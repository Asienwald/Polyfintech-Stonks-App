const _deploy_contracts = require("../migrations/2_deploy_contracts");

//test your smart contract with truffle
const StkToken = artifacts.require("./StkToken.sol");

contract("StkToken", (accounts) => {
    it("sets the total supply upon deployment", () => {
        return StkToken.deployed().then((instance) => {
            tokenInstance = instance;
            return tokenInstance.totalSupply();
        }).then((totalSupply) => {
            // check if supply equal to value we expect
            assert.equal(totalSupply.toNumber(), 1000000, 
                            "sets total supply to 1,000,000");
        })
    })
})