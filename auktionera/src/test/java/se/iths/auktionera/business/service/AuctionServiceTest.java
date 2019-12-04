package se.iths.auktionera.business.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import se.iths.auktionera.business.model.Auction;
import se.iths.auktionera.business.model.AuctionRequest;
import se.iths.auktionera.business.model.DeliveryType;
import se.iths.auktionera.persistence.entity.AccountEntity;
import se.iths.auktionera.persistence.entity.AuctionEntity;
import se.iths.auktionera.persistence.repo.AccountRepo;
import se.iths.auktionera.persistence.repo.AuctionRepo;

import java.time.Instant;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataJpaTest
class AuctionServiceTest {

    @MockBean
    AuctionRepo auctionRepo;

    @MockBean
    AccountRepo accountRepo;

    private IAuctionService auctionService;
    private AuctionEntity auctionEntity;
    private AuctionRequest auctionRequest;
    private AccountEntity accountEntity;

    @BeforeEach
    void setUp() {
        auctionService = new AuctionService(auctionRepo, accountRepo);
        accountEntity = AccountEntity.builder()
                .id(10)
                .anonymousBuyer(false)
                .authId("User")
                .city("City")
                .email("name@example.com")
                .postNr(12345)
                .streetName("Street 1")
                .userName("Usern")
                .createdAt(Instant.now())
                .build();

        auctionEntity = AuctionEntity.builder()
                .seller(accountEntity)
                .description("Laptop")
                .tags("Electronics")
                .endsAt(Instant.parse("2019-12-13T10:15:30Z"))
                .startPrice(100)
                .buyOutPrice(200)
                .minBidStep(10)
                .deliveryType(DeliveryType.PICKUPATADDRESS)
                .build();

        auctionRequest = new AuctionRequest();
        auctionRequest.setTags("Laptop");
        auctionRequest.setDescription("Electronics");
        auctionRequest.setEndsAt(Instant.parse("2019-12-13T10:15:30Z"));
        auctionRequest.setStartPrice(100);
        auctionRequest.setBuyoutPrice(200);
        auctionRequest.setMinBidStep(10);
        auctionRequest.setDeliveryType(DeliveryType.PICKUPATADDRESS);
    }

    @Test
    void createAuction() {
        //when(auctionRepo.saveAndFlush(any(AuctionEntity.class))).thenReturn(auctionEntity);
        when(accountRepo.findByAuthId(any())).thenReturn(accountEntity);

        Auction auctionAdded = auctionService.createAuction(accountEntity.getAuthId(), auctionRequest);
        assertNotNull(auctionAdded);
        assertThat(auctionAdded.getDescription(), is("Electronics"));
        assertThat(auctionAdded.getStartPrice(), is(100));
        assertThat(auctionAdded.getSeller().getId(), is(10L));
    }
}