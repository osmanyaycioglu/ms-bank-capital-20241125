package org.training.capital.microservice.mscustomeraccount;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Future;

public interface ICustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    CustomerAccount findLockByAccountId(Long accountId);

    List<CustomerAccount> findByAccountName(String accountName);

    Future<List<CustomerAccount>> findByAccountNameAndBonus(String accountName, BigDecimal bonus);

    // select * from customer_account c where c.account_name=?1 and c.discount=?2
    // select c from CustomerAccount c where c.accountName=?1 and c.discount=?2
    List<CustomerAccount> findByAccountNameAndDiscount(String accountName,
                                                       Integer discount);

    @Query("select c from CustomerAccount c where c.accountName=?1 and c.discount=?2")
    List<CustomerAccount> searchAccount(String accountName,
                                        Integer discount);

    @Query(value = "select * from customer_account c where c.account_name=?1 and c.discount=?2", nativeQuery = true)
    List<CustomerAccount> searchAccountNative(String accountName,
                                              Integer discount);

    @Query("select c.balance,c.bonus from CustomerAccount c where c.accountName=?1 and c.discount=?2")
    List<Object[]> searchAccountEx(String accountName,
                                   Integer discount);

    @Query("select new org.training.capital.microservice.mscustomer.db.models.AccountRootInfo(c.balance,c.bonus) from CustomerAccount c where c.accountName=?1 and c.discount=?2")
    List<AccountRootInfo> searchAccountRootInfo(String accountName,
                                                Integer discount);

    @Modifying
    @Query("update CustomerAccount u set u.balance = :bal where u.accountId= :ai")
    void updateBalance(@Param(value = "bal") BigDecimal balance,
                       @Param("ai") Long ai);

}
