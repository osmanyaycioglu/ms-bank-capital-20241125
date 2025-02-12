package org.training.capital.microservice.mscustomeraccount;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "customer_account")
// @EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@SequenceGenerator(name = "customer_account_seq", sequenceName = "customer_account_seq", allocationSize = 1, initialValue = 1)
public class CustomerAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_account_seq")
    private Long          accountId;
    @Column(name = "account_name", length = 30)
    @Size(max = 30)
    private String        accountName;
    @Column(name = "discount")
    private Integer       discount;
    @Column(name = "balance")
    private BigDecimal    balance;
    @Column(name = "bonus")
    private BigDecimal    bonus;
    private ZonedDateTime createDate;
    private ZonedDateTime updateDate;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private AccountInfo   accountInfo;

    private String        customerId;

    private int status = 1;

//    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//    private Set<AccountInfo> accountInfos;

    @Version
    private Long myVersion;

    @PrePersist
    public void createDateSet() {
        createDate = ZonedDateTime.now();
    }

    @PreUpdate
    public void updateDateSet() {
        updateDate = ZonedDateTime.now();
    }


}
