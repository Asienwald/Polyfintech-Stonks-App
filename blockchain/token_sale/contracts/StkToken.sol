// define ver of solidity
pragma solidity >=0.4.21 <0.7.0;

// refer to ERC-20 token standard
// this contract knows who has each token
contract StkToken {
    // name
    string public name = "Stonks Token";

    // symbol
    string public symbol = "STNK";

    // not part of erc-20 standard but just shows your ver of smart contract
    string public standard = "STNK Token v1.0";

    // unsigned public int
    // the public visibility auto adds a getter func to this var
    uint256 public totalSupply;

    // mapping(key, value) = hashmap<key, value> in java
    // this mapping responsible in knowing who has which token
    mapping(address => uint256) public balanceOf;

    // declare a transfer event
    event Transfer(
        address indexed _from,
        address indexed _to,
        uint256 _value
    );


    // Constructor - run whenever smart contract deployed
    // Set total num of tokens
    // read total num of tokens
    constructor(uint256 _initialSupply) public{
        // msg is global var in solidity
        // sender is address that called this function
        // read official docs of solidity pls thx
        balanceOf[msg.sender] = _initialSupply;
        
        // num of tokens that will exist
        // state var - kinda like a class var in other lang
        totalSupply = _initialSupply;

        // allocate initial supply

    }

    // transfer event - throws exception if acc dont have enough, return bool
    function transfer(address _to, uint256 _value) public returns (bool success){
        // if not enough tokens to transfer, raise exception
        // require (func in solidity) - if condition is true, continue function execution; if false, throw error
        require(balanceOf[msg.sender] >= _value);

        balanceOf[msg.sender] -= _value;
        balanceOf[_to] += _value;

        emit Transfer(msg.sender, _to, _value);  // call event in solidity

        return true;
    }
}