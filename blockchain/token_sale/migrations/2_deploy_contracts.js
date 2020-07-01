const StkToken = artifacts.require("./StkToken.sol");

// where all our migration files will go
module.exports = function(deployer) {
  deployer.deploy(StkToken, 1000000);
};

/*
when we deploy smart contracts, we are actually modifying the blockchain's state

whenever create new smart contract we write new file to migrations dir that 
will handle the migration of that contract to the blockchain
*/