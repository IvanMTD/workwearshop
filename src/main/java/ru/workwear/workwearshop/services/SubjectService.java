package ru.workwear.workwearshop.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.workwear.workwearshop.dto.SubjectDTO;
import ru.workwear.workwearshop.models.Subject;
import ru.workwear.workwearshop.repositories.SubjectRepository;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
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
        }else{
            System.out.println(optionalSubject.get().toString());
        }
        return new SubjectUserDetail(optionalSubject.get());
    }

    @Data
    @RequiredArgsConstructor
    class SubjectUserDetail implements UserDetails{
        @Serial
        private static final long serialVersionUID = 1L;

        private final Subject subject;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(new SimpleGrantedAuthority(subject.getRole().getRole()));
        }

        @Override
        public String getPassword() {
            return subject.getPassword();
        }

        @Override
        public String getUsername() {
            return subject.getUsername();
        }

        @Override
        public boolean isAccountNonExpired() {
            return false;
        }

        @Override
        public boolean isAccountNonLocked() {
            return false;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return false;
        }

        @Override
        public boolean isEnabled() {
            return subject.isEnabled();
        }
    }
}
