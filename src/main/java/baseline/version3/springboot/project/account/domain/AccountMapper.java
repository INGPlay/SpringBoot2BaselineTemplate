package baseline.version3.springboot.project.account.domain;

import baseline.version3.springboot.project.account.entity.Account;
import org.mapstruct.*;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,    // "spring"
        injectionStrategy = InjectionStrategy.FIELD
)
public abstract class AccountMapper {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mappings({
        @Mapping(expression = "java( passwordEncoder.encode(registerAccountDTO.getAccountPassword()) )", target = "accountPassword")
    })
    public abstract Account createAccount(AccountRequest.RegisterAccountDTO registerAccountDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(expression = "java( passwordEncoder.encode(updateAccountDTO.getAccountPassword()) )", target = "accountPassword")
    })
    public abstract void updateAccount(AccountRequest.UpdateAccountDTO updateAccountDTO, @MappingTarget Account account);
}
