const { assert } = require("chai");

const Token = artifacts.require("StkToken");
const EthSwap = artifacts.require("EthSwap");

require("chai")
    .use(require("chai-as-promised"))
    .should();

function tokens(n){
    return web3.utils.toWei(n, "ether");
}

contract("EthSwap", (accounts) => {

    let token, swap;

    // called before each test func
    before(async() => {
        token = await Token.new();
        swap = await EthSwap.new();
        await token.transfer(swap.address, tokens("1000000"));
    })

    describe("Token deployment", async () => {
        it("contract has name", async() => {
            const name = await token.name();
            assert.equal(name, "Stonks Token")
        })
    })
    describe("EthSwap deployment", async () => {
        it("contract has name", async() => {
            const name = await swap.name();
            assert.equal(name, "EthSwap Instance Exchange")
        })

        it("contract has tokens", async() => {
            let balance = await token.balanceOf(swap.address);
            assert.equal(balance.toString(), tokens("1000000"))
        })
    })
})