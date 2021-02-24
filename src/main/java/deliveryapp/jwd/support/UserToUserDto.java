package deliveryapp.jwd.support;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import deliveryapp.jwd.model.User;
import deliveryapp.jwd.web.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserToUserDto implements Converter<User, UserDto>{

  
    @Override
    public UserDto convert(User user) {
    	UserDto userDto = new UserDto();
    	
    	userDto.setId(user.getId());
    	userDto.setEmail(user.geteMail());
    	userDto.setFirstName(user.getName());
    	userDto.setLastName(user.getLastName());
    	userDto.setUsername(user.getUserName());

        return userDto;
    }

    public List<UserDto> convert(List<User> users){
        List<UserDto> userDtos = new ArrayList<>();

        for(User u : users) {
        	UserDto dto = convert(u);
        	userDtos.add(dto);
        }

        return userDtos;
    }
}
