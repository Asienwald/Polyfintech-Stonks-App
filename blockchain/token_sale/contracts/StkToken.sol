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
    // allowance public mapping - A approve B to C amount
    mapping(address => mapping(address => uint256)) public allowance;

    // declare a transfer event
    event Transfer(
        address indexed _from,
        address indexed _to,
        uint256 _value
    );

    // approval event
    // me, the owner approve acc B (spender) to spend C (_value) amount of tokens
    event Approval(
        address indexed _owner,
        address indexed _spender,
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

    // transfer func - throws exception if acc dont have enough, return bool
    function transfer(address _to, uint256 _value) public returns (bool success){
        // if not enough tokens to transfer, raise exception
        // require (func in solidity) - if condition is true, continue function execution; if false, throw error
        require(balanceOf[msg.sender] >= _value);

        balanceOf[msg.sender] -= _value;
        balanceOf[_to] += _value;

        emit Transfer(msg.sender, _to, _value);  // call event in solidity

        return true;
    }


    // delegated transfer - transfer where acc didnt initiate transfer
    // AKA meta transaction, another account (delegate) performs intended action on account's behalf
    // one func to approve transfer, another to handle actual delegated transfer

    // approve func - allows someone else to spend x tokens on their behalf
    function approve(address _spender, uint256 _value) public returns (bool success){
        // set allowance to 0 first before setting to another value
        // allowance - alloted amount approved to transfer
        allowance[msg.sender][_spender] = _value;

        // approval event
        emit Approval(msg.sender, _spender, _value);

        return true;
    }

    // transferFrom func - when amt approved, can execute that transfer
    function transferFrom(address _from, address _to, uint256 _value) public returns (bool success){
        // require _from has enough tokens and allowance is big enough
        require(_value <= balanceOf[_from]);
        require(_value <= allowance[_from][msg.sender]);

        // change balanceOf
        balanceOf[_from] -= _value;
        balanceOf[_to] += _value;

        // update allowance
        allowance[_from][msg.sender] -= _value;
        
        // Transfer event
        emit Transfer(_from, _to, _value);

        return true;
    }
}