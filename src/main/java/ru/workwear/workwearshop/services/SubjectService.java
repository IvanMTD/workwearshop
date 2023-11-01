package ru.workwear.workwearshop.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.workwear.workwearshop.dto.SubjectDTO;
import ru.workwear.workwearshop.models.Subject;
import ru.workwear.workwearshop.repositories.SubjectRepository;

import java.util.Optional;

@Data
@Service
@RequiredArgsConstructor
public class SubjectService implements UserDetailsService {
    private final SubjectRepository subjectRepository;

    public Subject save(SubjectDTO subjectDTO, PasswordEncoder passwordEncoder){
        Subject subject = new Subject();
        subject.setUsername(subjectDTO.getUsername());
        subject.setPassword(passwordEncoder.encode(subjectDTO.getPassword()));
        subject.setBirthday(subjectDTO.getBirthday());
        subject.setRole(subjectDTO.getRole());
        subject.setEnabled(subjectDTO.isEnabled());
        return subjectRepository.save(subject);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Subject> optionalSubject = subjectRepository.findByUsername(username);
        if(optionalSubject.isEmpty()){
            throw new UsernameNotFoundException("Subject '" + username + "' is not found");
        }
        return optionalSubject.get();
    }
}
