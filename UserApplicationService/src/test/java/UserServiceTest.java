import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.models.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    KafkaTemplate<String,String> kafkaTemplate;

    @InjectMocks
    UserService userService;


    @Test()
    public void testUserCreation() throws JsonProcessingException {
        User user=User.builder()
                .name("Something")
                .age(20).email("vai@gmail.com")
                .phone("244423")
                .build();

        //I have to check if this method is doing atleast one call to userRepository

        userService.create(user);

        //after this call
        //did I use user repo exactly once
        //did I use kafka exactly once

        verify(userRepository, times(1)).save(any());

        verify(kafkaTemplate,times(1)).send(eq("user_created"),any());

    }
}
