const StkToken = artifacts.require("StkToken");
const EthSwap = artifacts.require("EthSwap");

// where all our migration files will go
module.exports = async function(deployer) {
  // deploy stktoken
  await deployer.deploy(StkToken);
  const token = await StkToken.deployed();

  // deploy ethswap
  await deployer.deploy(EthSwap);
  const ethSwap = await EthSwap.deployed();

  // transfer tokens to ethswap
  await token.transfer(ethSwap.address, "1000000000000000000000000");
};

/*
when we deploy smart contracts, we are actually modifying the blockchain's state

whenever create new smart contract we write new file to migrations dir that 
will handle the migration of that contract to the blockchain
*/