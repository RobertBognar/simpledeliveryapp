package deliveryapp.jwd.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import deliveryapp.jwd.model.User;
import deliveryapp.jwd.service.UserService;
import deliveryapp.jwd.web.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;

@Component
public class UserDtoToUser implements Converter<UserDto, User> {

    @Autowired
    private UserService userService;


    @Override
    public User convert(UserDto userDTO) {
    	User user = null;
        if(userDTO.getId() != null) {
        	user = userService.one(userDTO.getId()).get();
        }

        if(user == null) {
        	user = new User();
        }

        user.setUserName(userDTO.getUsername());
        user.seteMail(userDTO.getEmail());
        user.setName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        

        return user;
    }

}
