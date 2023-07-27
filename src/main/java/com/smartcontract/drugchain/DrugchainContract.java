package com.smartcontract.drugchain;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.5.0.
 */
@SuppressWarnings("rawtypes")
public class DrugchainContract extends Contract {
    public static final String BINARY = "60806040526000805534801561001457600080fd5b5061244a806100246000396000f3fe608060405234801561001057600080fd5b50600436106100885760003560e01c80637acc0b201161005b5780637acc0b2014610115578063c324b3fb1461014b578063d7dbb89814610167578063f5e2c8a31461019757610088565b8063235fe0421461008d5780633e1a9300146100ab5780636813b53b146100c95780636d072c2a146100f9575b600080fd5b6100956101c7565b6040516100a291906116a6565b60405180910390f35b6100b36101cd565b6040516100c091906116a6565b60405180910390f35b6100e360048036038101906100de9190611701565b6101d6565b6040516100f09190611885565b60405180910390f35b610113600480360381019061010e9190611a70565b610500565b005b61012f600480360381019061012a9190611701565b610af2565b6040516101429796959493929190611c10565b60405180910390f35b61016560048036038101906101609190611ca2565b610dec565b005b610181600480360381019061017c9190611cfe565b610e68565b60405161018e91906116a6565b60405180910390f35b6101b160048036038101906101ac9190611cfe565b61126e565b6040516101be9190611ea5565b60405180910390f35b60005481565b60008054905090565b6101de611650565b600282815481106101f2576101f1611ec7565b5b90600052602060002090600702016040518060e001604052908160008201805461021b90611f25565b80601f016020809104026020016040519081016040528092919081815260200182805461024790611f25565b80156102945780601f1061026957610100808354040283529160200191610294565b820191906000526020600020905b81548152906001019060200180831161027757829003601f168201915b505050505081526020016001820180546102ad90611f25565b80601f01602080910402602001604051908101604052809291908181526020018280546102d990611f25565b80156103265780601f106102fb57610100808354040283529160200191610326565b820191906000526020600020905b81548152906001019060200180831161030957829003601f168201915b5050505050815260200160028201805461033f90611f25565b80601f016020809104026020016040519081016040528092919081815260200182805461036b90611f25565b80156103b85780601f1061038d576101008083540402835291602001916103b8565b820191906000526020600020905b81548152906001019060200180831161039b57829003601f168201915b505050505081526020016003820180546103d190611f25565b80601f01602080910402602001604051908101604052809291908181526020018280546103fd90611f25565b801561044a5780601f1061041f5761010080835404028352916020019161044a565b820191906000526020600020905b81548152906001019060200180831161042d57829003601f168201915b5050505050815260200160048201805461046390611f25565b80601f016020809104026020016040519081016040528092919081815260200182805461048f90611f25565b80156104dc5780601f106104b1576101008083540402835291602001916104dc565b820191906000526020600020905b8154815290600101906020018083116104bf57829003601f168201915b50505050508152602001600582015481526020016006820154815250509050919050565b7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff830361072c5761052f611650565b898160000181905250888160200181905250878160400181905250868160600181905250848160800181905250858160c00181815250506000548160a001818152505060008081548092919061058490611f85565b9190505550600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081908060018154018082558091505060019003906000526020600020906007020160009091909190915060008201518160000190816106079190612179565b50602082015181600101908161061d9190612179565b5060408201518160020190816106339190612179565b5060608201518160030190816106499190612179565b50608082015181600401908161065f9190612179565b5060a0820151816005015560c082015181600601555050600281908060018154018082558091505060019003906000526020600020906007020160009091909190915060008201518160000190816106b79190612179565b5060208201518160010190816106cd9190612179565b5060408201518160020190816106e39190612179565b5060608201518160030190816106f99190612179565b50608082015181600401908161070f9190612179565b5060a0820151816005015560c08201518160060155505050610ae7565b60005b600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020805490508110156107fd5783600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002082815481106107cb576107ca611ec7565b5b906000526020600020906007020160050154036107ea578093506107fd565b80806107f59061224b565b91505061072f565b5084600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002084815481106108505761084f611ec7565b5b906000526020600020906007020160060154101561086d57600080fd5b610875611650565b898160000181905250888160200181905250878160400181905250868160600181905250848160800181905250858160c00181815250506000548160a00181815250506000808154809291906108ca90611f85565b9190505550600281908060018154018082558091505060019003906000526020600020906007020160009091909190915060008201518160000190816109109190612179565b5060208201518160010190816109269190612179565b50604082015181600201908161093c9190612179565b5060608201518160030190816109529190612179565b5060808201518160040190816109689190612179565b5060a0820151816005015560c08201518160060155505085600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002085815481106109d1576109d0611ec7565b5b906000526020600020906007020160060160008282546109f19190612293565b92505081905550600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190806001815401808255809150506001900390600052602060002090600702016000909190919091506000820151816000019081610a769190612179565b506020820151816001019081610a8c9190612179565b506040820151816002019081610aa29190612179565b506060820151816003019081610ab89190612179565b506080820151816004019081610ace9190612179565b5060a0820151816005015560c082015181600601555050505b505050505050505050565b60028181548110610b0257600080fd5b9060005260206000209060070201600091509050806000018054610b2590611f25565b80601f0160208091040260200160405190810160405280929190818152602001828054610b5190611f25565b8015610b9e5780601f10610b7357610100808354040283529160200191610b9e565b820191906000526020600020905b815481529060010190602001808311610b8157829003601f168201915b505050505090806001018054610bb390611f25565b80601f0160208091040260200160405190810160405280929190818152602001828054610bdf90611f25565b8015610c2c5780601f10610c0157610100808354040283529160200191610c2c565b820191906000526020600020905b815481529060010190602001808311610c0f57829003601f168201915b505050505090806002018054610c4190611f25565b80601f0160208091040260200160405190810160405280929190818152602001828054610c6d90611f25565b8015610cba5780601f10610c8f57610100808354040283529160200191610cba565b820191906000526020600020905b815481529060010190602001808311610c9d57829003601f168201915b505050505090806003018054610ccf90611f25565b80601f0160208091040260200160405190810160405280929190818152602001828054610cfb90611f25565b8015610d485780601f10610d1d57610100808354040283529160200191610d48565b820191906000526020600020905b815481529060010190602001808311610d2b57829003601f168201915b505050505090806004018054610d5d90611f25565b80601f0160208091040260200160405190810160405280929190818152602001828054610d8990611f25565b8015610dd65780601f10610dab57610100808354040283529160200191610dd6565b820191906000526020600020905b815481529060010190602001808311610db957829003601f168201915b5050505050908060050154908060060154905087565b60028281548110610e0057610dff611ec7565b5b906000526020600020906007020160040181604051602001610e239291906123d2565b60405160208183030381529060405260028381548110610e4657610e45611ec7565b5b90600052602060002090600702016004019081610e639190612179565b505050565b600080600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020805480602002602001604051908101604052809291908181526020016000905b828210156111ea57838290600052602060002090600702016040518060e0016040529081600082018054610efd90611f25565b80601f0160208091040260200160405190810160405280929190818152602001828054610f2990611f25565b8015610f765780601f10610f4b57610100808354040283529160200191610f76565b820191906000526020600020905b815481529060010190602001808311610f5957829003601f168201915b50505050508152602001600182018054610f8f90611f25565b80601f0160208091040260200160405190810160405280929190818152602001828054610fbb90611f25565b80156110085780601f10610fdd57610100808354040283529160200191611008565b820191906000526020600020905b815481529060010190602001808311610feb57829003601f168201915b5050505050815260200160028201805461102190611f25565b80601f016020809104026020016040519081016040528092919081815260200182805461104d90611f25565b801561109a5780601f1061106f5761010080835404028352916020019161109a565b820191906000526020600020905b81548152906001019060200180831161107d57829003601f168201915b505050505081526020016003820180546110b390611f25565b80601f01602080910402602001604051908101604052809291908181526020018280546110df90611f25565b801561112c5780601f106111015761010080835404028352916020019161112c565b820191906000526020600020905b81548152906001019060200180831161110f57829003601f168201915b5050505050815260200160048201805461114590611f25565b80601f016020809104026020016040519081016040528092919081815260200182805461117190611f25565b80156111be5780601f10611193576101008083540402835291602001916111be565b820191906000526020600020905b8154815290600101906020018083116111a157829003601f168201915b505050505081526020016005820154815260200160068201548152505081526020019060010190610eca565b5050505090506000600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206001835161123f9190612293565b815481106112505761124f611ec7565b5b90600052602060002090600702016005015490508092505050919050565b60606000600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020805490501461164457600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020805480602002602001604051908101604052809291908181526020016000905b8282101561163957838290600052602060002090600702016040518060e001604052908160008201805461134c90611f25565b80601f016020809104026020016040519081016040528092919081815260200182805461137890611f25565b80156113c55780601f1061139a576101008083540402835291602001916113c5565b820191906000526020600020905b8154815290600101906020018083116113a857829003601f168201915b505050505081526020016001820180546113de90611f25565b80601f016020809104026020016040519081016040528092919081815260200182805461140a90611f25565b80156114575780601f1061142c57610100808354040283529160200191611457565b820191906000526020600020905b81548152906001019060200180831161143a57829003601f168201915b5050505050815260200160028201805461147090611f25565b80601f016020809104026020016040519081016040528092919081815260200182805461149c90611f25565b80156114e95780601f106114be576101008083540402835291602001916114e9565b820191906000526020600020905b8154815290600101906020018083116114cc57829003601f168201915b5050505050815260200160038201805461150290611f25565b80601f016020809104026020016040519081016040528092919081815260200182805461152e90611f25565b801561157b5780601f106115505761010080835404028352916020019161157b565b820191906000526020600020905b81548152906001019060200180831161155e57829003601f168201915b5050505050815260200160048201805461159490611f25565b80601f01602080910402602001604051908101604052809291908181526020018280546115c090611f25565b801561160d5780601f106115e25761010080835404028352916020019161160d565b820191906000526020600020905b8154815290600101906020018083116115f057829003601f168201915b505050505081526020016005820154815260200160068201548152505081526020019060010190611319565b50505050905061164b565b6060809150505b919050565b6040518060e00160405280606081526020016060815260200160608152602001606081526020016060815260200160008152602001600081525090565b6000819050919050565b6116a08161168d565b82525050565b60006020820190506116bb6000830184611697565b92915050565b6000604051905090565b600080fd5b600080fd5b6116de8161168d565b81146116e957600080fd5b50565b6000813590506116fb816116d5565b92915050565b600060208284031215611717576117166116cb565b5b6000611725848285016116ec565b91505092915050565b600081519050919050565b600082825260208201905092915050565b60005b8381101561176857808201518184015260208101905061174d565b60008484015250505050565b6000601f19601f8301169050919050565b60006117908261172e565b61179a8185611739565b93506117aa81856020860161174a565b6117b381611774565b840191505092915050565b6117c78161168d565b82525050565b600060e08301600083015184820360008601526117ea8282611785565b915050602083015184820360208601526118048282611785565b9150506040830151848203604086015261181e8282611785565b915050606083015184820360608601526118388282611785565b915050608083015184820360808601526118528282611785565b91505060a083015161186760a08601826117be565b5060c083015161187a60c08601826117be565b508091505092915050565b6000602082019050818103600083015261189f81846117cd565b905092915050565b600080fd5b600080fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6118e982611774565b810181811067ffffffffffffffff82111715611908576119076118b1565b5b80604052505050565b600061191b6116c1565b905061192782826118e0565b919050565b600067ffffffffffffffff821115611947576119466118b1565b5b61195082611774565b9050602081019050919050565b82818337600083830152505050565b600061197f61197a8461192c565b611911565b90508281526020810184848401111561199b5761199a6118ac565b5b6119a684828561195d565b509392505050565b600082601f8301126119c3576119c26118a7565b5b81356119d384826020860161196c565b91505092915050565b6000819050919050565b6119ef816119dc565b81146119fa57600080fd5b50565b600081359050611a0c816119e6565b92915050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000611a3d82611a12565b9050919050565b611a4d81611a32565b8114611a5857600080fd5b50565b600081359050611a6a81611a44565b92915050565b60008060008060008060008060006101208a8c031215611a9357611a926116cb565b5b60008a013567ffffffffffffffff811115611ab157611ab06116d0565b5b611abd8c828d016119ae565b99505060208a013567ffffffffffffffff811115611ade57611add6116d0565b5b611aea8c828d016119ae565b98505060408a013567ffffffffffffffff811115611b0b57611b0a6116d0565b5b611b178c828d016119ae565b97505060608a013567ffffffffffffffff811115611b3857611b376116d0565b5b611b448c828d016119ae565b9650506080611b558c828d016116ec565b95505060a08a013567ffffffffffffffff811115611b7657611b756116d0565b5b611b828c828d016119ae565b94505060c0611b938c828d016119fd565b93505060e0611ba48c828d01611a5b565b925050610100611bb68c828d01611a5b565b9150509295985092959850929598565b600082825260208201905092915050565b6000611be28261172e565b611bec8185611bc6565b9350611bfc81856020860161174a565b611c0581611774565b840191505092915050565b600060e0820190508181036000830152611c2a818a611bd7565b90508181036020830152611c3e8189611bd7565b90508181036040830152611c528188611bd7565b90508181036060830152611c668187611bd7565b90508181036080830152611c7a8186611bd7565b9050611c8960a0830185611697565b611c9660c0830184611697565b98975050505050505050565b60008060408385031215611cb957611cb86116cb565b5b6000611cc7858286016116ec565b925050602083013567ffffffffffffffff811115611ce857611ce76116d0565b5b611cf4858286016119ae565b9150509250929050565b600060208284031215611d1457611d136116cb565b5b6000611d2284828501611a5b565b91505092915050565b600081519050919050565b600082825260208201905092915050565b6000819050602082019050919050565b600060e0830160008301518482036000860152611d748282611785565b91505060208301518482036020860152611d8e8282611785565b91505060408301518482036040860152611da88282611785565b91505060608301518482036060860152611dc28282611785565b91505060808301518482036080860152611ddc8282611785565b91505060a0830151611df160a08601826117be565b5060c0830151611e0460c08601826117be565b508091505092915050565b6000611e1b8383611d57565b905092915050565b6000602082019050919050565b6000611e3b82611d2b565b611e458185611d36565b935083602082028501611e5785611d47565b8060005b85811015611e935784840389528151611e748582611e0f565b9450611e7f83611e23565b925060208a01995050600181019050611e5b565b50829750879550505050505092915050565b60006020820190508181036000830152611ebf8184611e30565b905092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b60006002820490506001821680611f3d57607f821691505b602082108103611f5057611f4f611ef6565b5b50919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000611f908261168d565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8203611fc257611fc1611f56565b5b600182019050919050565b60008190508160005260206000209050919050565b60006020601f8301049050919050565b600082821b905092915050565b60006008830261202f7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82611ff2565b6120398683611ff2565b95508019841693508086168417925050509392505050565b6000819050919050565b600061207661207161206c8461168d565b612051565b61168d565b9050919050565b6000819050919050565b6120908361205b565b6120a461209c8261207d565b848454611fff565b825550505050565b600090565b6120b96120ac565b6120c4818484612087565b505050565b5b818110156120e8576120dd6000826120b1565b6001810190506120ca565b5050565b601f82111561212d576120fe81611fcd565b61210784611fe2565b81016020851015612116578190505b61212a61212285611fe2565b8301826120c9565b50505b505050565b600082821c905092915050565b600061215060001984600802612132565b1980831691505092915050565b6000612169838361213f565b9150826002028217905092915050565b6121828261172e565b67ffffffffffffffff81111561219b5761219a6118b1565b5b6121a58254611f25565b6121b08282856120ec565b600060209050601f8311600181146121e357600084156121d1578287015190505b6121db858261215d565b865550612243565b601f1984166121f186611fcd565b60005b82811015612219578489015182556001820191506020850194506020810190506121f4565b868310156122365784890151612232601f89168261213f565b8355505b6001600288020188555050505b505050505050565b6000612256826119dc565b91507f7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff820361228857612287611f56565b5b600182019050919050565b600061229e8261168d565b91506122a98361168d565b92508282039050818111156122c1576122c0611f56565b5b92915050565b600081905092915050565b600081546122df81611f25565b6122e981866122c7565b9450600182166000811461230457600181146123195761234c565b60ff198316865281151582028601935061234c565b61232285611fcd565b60005b8381101561234457815481890152600182019150602081019050612325565b838801955050505b50505092915050565b7f2c20000000000000000000000000000000000000000000000000000000000000815250565b7f74786e5f6964203a200000000000000000000000000000000000000000000000815250565b60006123ac8261172e565b6123b681856122c7565b93506123c681856020860161174a565b80840191505092915050565b60006123de82856122d2565b91506123e982612355565b6002820191506123f88261237b565b60098201915061240882846123a1565b9150819050939250505056fea2646970667358221220187d294dbef3d059c8949b3d6f6afe9555759d5f91b181b419449ddf2337f7ac64736f6c63430008120033";

    public static final String FUNC_CREATEPRODUCT = "createProduct";

    public static final String FUNC_FINDPRODUCT = "findProduct";

    public static final String FUNC_GETLASTINDEX = "getLastIndex";

    public static final String FUNC_GETLASTPRODUCTID = "getLastProductId";

    public static final String FUNC_GETPRODUCTDETAILS = "getProductDetails";

    public static final String FUNC_PRODUCTS = "products";

    public static final String FUNC_RUNNINGPRODUCTID = "runningProductId";

    public static final String FUNC_UPDATEHISTORY = "updateHistory";

    @Deprecated
    protected DrugchainContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DrugchainContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DrugchainContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DrugchainContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> createProduct(String item_name, String mfg_date, String expiry_date, String batch_no, BigInteger numOfItem, String history, BigInteger parent, String user_address, String to_Address) {
        final Function function = new Function(
                FUNC_CREATEPRODUCT, 
                Arrays.<Type>asList(new Utf8String(item_name),
                new Utf8String(mfg_date),
                new Utf8String(expiry_date),
                new Utf8String(batch_no),
                new Uint256(numOfItem),
                new Utf8String(history),
                new org.web3j.abi.datatypes.generated.Int256(parent), 
                new org.web3j.abi.datatypes.Address(160, user_address), 
                new org.web3j.abi.datatypes.Address(160, to_Address)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> findProduct(String person) {
        final Function function = new Function(FUNC_FINDPRODUCT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, person)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Product>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> getLastIndex() {
        final Function function = new Function(FUNC_GETLASTINDEX, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getLastProductId(String addr) {
        final Function function = new Function(FUNC_GETLASTPRODUCTID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, addr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Product> getProductDetails(BigInteger productId) {
        final Function function = new Function(FUNC_GETPRODUCTDETAILS, 
                Arrays.<Type>asList(new Uint256(productId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Product>() {}));
        return executeRemoteCallSingleValueReturn(function, Product.class);
    }

    public RemoteFunctionCall<Tuple7<String, String, String, String, String, BigInteger, BigInteger>> products(BigInteger param0) {
        final Function function = new Function(FUNC_PRODUCTS, 
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple7<String, String, String, String, String, BigInteger, BigInteger>>(function,
                new Callable<Tuple7<String, String, String, String, String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple7<String, String, String, String, String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<String, String, String, String, String, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (String) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> runningProductId() {
        final Function function = new Function(FUNC_RUNNINGPRODUCTID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> updateHistory(BigInteger pid, String hist) {
        final Function function = new Function(
                FUNC_UPDATEHISTORY, 
                Arrays.<Type>asList(new Uint256(pid),
                new Utf8String(hist)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static DrugchainContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DrugchainContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DrugchainContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DrugchainContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DrugchainContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new DrugchainContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DrugchainContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DrugchainContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<DrugchainContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DrugchainContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DrugchainContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DrugchainContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<DrugchainContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DrugchainContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DrugchainContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DrugchainContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class Product extends DynamicStruct {
        public String itemName;

        public String mfgDate;

        public String expiryDate;

        public String batchNo;

        public String current_history;

        public BigInteger ID;

        public BigInteger numberOfItem;

        public Product(String itemName, String mfgDate, String expiryDate, String batchNo, String current_history, BigInteger ID, BigInteger numberOfItem) {
            super(new Utf8String(itemName),
                    new Utf8String(mfgDate),
                    new Utf8String(expiryDate),
                    new Utf8String(batchNo),
                    new Utf8String(current_history),
                    new Uint256(ID),
                    new Uint256(numberOfItem));
            this.itemName = itemName;
            this.mfgDate = mfgDate;
            this.expiryDate = expiryDate;
            this.batchNo = batchNo;
            this.current_history = current_history;
            this.ID = ID;
            this.numberOfItem = numberOfItem;
        }

        public Product(Utf8String itemName, Utf8String mfgDate, Utf8String expiryDate, Utf8String batchNo, Utf8String current_history, Uint256 ID, Uint256 numberOfItem) {
            super(itemName, mfgDate, expiryDate, batchNo, current_history, ID, numberOfItem);
            this.itemName = itemName.getValue();
            this.mfgDate = mfgDate.getValue();
            this.expiryDate = expiryDate.getValue();
            this.batchNo = batchNo.getValue();
            this.current_history = current_history.getValue();
            this.ID = ID.getValue();
            this.numberOfItem = numberOfItem.getValue();
        }
    }
}