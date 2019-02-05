package yanmakes.employee_management.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomUserDetails extends Employee implements UserDetails {

    public CustomUserDetails(){}


    public CustomUserDetails(final Employee employee){
        super(employee);
        System.out.println(super.toString());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> list=getRoles().
                stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.getRole()))
                .collect(Collectors.toList());

        return list;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return super.isActive();
    }
}

