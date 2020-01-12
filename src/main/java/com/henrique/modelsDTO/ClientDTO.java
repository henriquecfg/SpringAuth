package com.henrique.modelsDTO;

import com.henrique.models.Client;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class ClientDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    @NotEmpty(message = "Obligatory")
    @Length(min = 5, max = 120, message = "Must have between 5 and 120 characters")
    private String name;

    @NotEmpty(message = "Invalid")
    @Email(message = "Invalid Email")
    private String email;

    @NotEmpty(message = "Obligatory")
    @Length(min = 5, max = 120, message = "Must have at least 5 characters")
    private String password;

    public ClientDTO(){}
    public ClientDTO(Client obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.email = obj.getEmail();
        this.password = obj.getPassword();
    }
}
