/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Random;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import module.EntityEJB;


/**
 *
 * @author jvm
 */
@Named(value = "userNamberBean")
@SessionScoped
public class WebController implements Serializable {

    Integer randomInt;
    Integer userNumber;
    String response;
    @Inject
    private EntityEJB entityEJB;

    /**
     * Creates a new instance of UserNamberBean
     */
    public WebController() {
        Random randomGR = new Random();
        Integer randInt =  randomGR.nextInt(10);
        setRandomInt(randInt);
        System.out.println("Duke's number: " + randomInt);
    }

    public Integer getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Integer userNumber) {
        this.userNumber = userNumber;
        entityEJB.setUserNumber(userNumber);
    }
    

    
    public String getResponse() {
        if ((userNumber != null) && (userNumber.compareTo(randomInt) == 0)) {

            //invalidate user session
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.invalidate();

            return "Yay! You got it!";
        } else {

            return "<p>Sorry, " + userNumber + " isn't it.</p>"
                    + "<p>Guess again...</p>";
        }
    }

    private void setRandomInt(Integer randomInt) {
        this.randomInt=randomInt;
        entityEJB.setDukeNumber(randomInt);
    }
}
