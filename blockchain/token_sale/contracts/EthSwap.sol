pragma solidity >=0.4.21 <0.7.0;

// import another smart contract to use
import "./StkToken.sol";

contract EthSwap{
    string public name = "EthSwap Instance Exchange";
    StkToken public token;

    constructor() public {
        
    }
}
