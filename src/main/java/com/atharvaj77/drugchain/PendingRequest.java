package com.atharvaj77.drugchain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PendingRequest {
    private @Getter @Setter String message;
    private @Getter @Setter String numOfItem;
    private @Getter @Setter String productName;
    private @Getter @Setter String reqFrom;
    private @Getter @Setter String reqId;
    private @Getter @Setter String web3AddressFrom;
}
