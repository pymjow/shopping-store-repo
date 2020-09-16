package com.userservice.infrastructure;

import com.userservice.document.model.entity.UserProfile;
import com.userservice.document.model.valueobjects.Contact;
import com.userservice.document.model.valueobjects.Location;
import com.userservice.document.model.valueobjects.PersonalInfo;
import com.userservice.infrastructure.repositories.UserProfileRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserProfileRepositoryTest {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Test
    public void testFetchAllUser() {

        List<UserProfile> userProfileList = userProfileRepository.findAll();
        assertThat(userProfileList.isEmpty()).isEqualTo(false);
    }

    @Test
    public void testFetchUserById() {

        Optional<UserProfile> userProfile = userProfileRepository.findById(1L);
        assertThat(userProfile.isPresent()).isEqualTo(true);

    }

    @Test
    public void saveUserProfile() {

        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setBirthDate(LocalDate.now());
        personalInfo.setFirstName("milad");
        personalInfo.setLastName("jowkar");

        Contact contact = new Contact();
        contact.setEmail("test@gmail.com");
        contact.setMobileNumber("+975654288770");

        Location location = new Location();
        location.setAddress("Esfahan keshvari 995");
        location.setZipCode("8878451290");

        UserProfile userProfile = new UserProfile();
        userProfile.setId(3L);
        userProfile.setContact(contact);
        userProfile.setLocation(location);
        userProfile.setPersonalInfo(personalInfo);

        userProfile = userProfileRepository.save(userProfile);

        assertThat(userProfile.getId()).isNotNull();

    }


}
