// define ver of solidity
pragma solidity >=0.4.21 <0.7.0;

// refer to ERC-20 token standard

contract StkToken {
    // Constructor - run whenever smart contract deployed
    // Set total num of tokens
    // read total num of tokens

    // unsigned public int
    // the public visibility auto adds a getter func to this var
    uint256 public totalSupply;

    constructor() public{
        // num of tokens that will exist
        // state var - kinda like a class var in other lang
        totalSupply = 1000000;
    }
}