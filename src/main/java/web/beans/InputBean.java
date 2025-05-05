package web.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import web.tables.Result;

import java.util.ResourceBundle;

@Named("inputBean")
@RequestScoped
public class InputBean {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("locales/messages");

    @Getter
    @Setter
    private Result result = new Result();

    @Inject
    private ValidationBean validationBean;

    @Inject
    private ResultBean resultBean;

    public String processInput() {
        try {
            validationBean.validateInput(result);
            resultBean.setResult(result);
            resultBean.checkHit();
        } catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,  BUNDLE.getString("error.validation"),
                            ex.getMessage()));
        }
        return "main?faces-redirect=true";
    }
}