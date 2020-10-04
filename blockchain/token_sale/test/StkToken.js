//test your smart contract with truffle
// const StkToken = artifacts.require("StkToken");

// accounts refer to all accounts we have in ganache
// contract("StkToken", (accounts) => {
//     var tokenInstance;

//     it("Initialises contract with correct values", () => {
//         return StkToken.deployed().then((instance) => {
//             tokenInstance = instance;
//             return tokenInstance.name();
//         }).then((name) => {
//             assert.equal(name, "Stonks Token", "has correct name");

//             return tokenInstance.symbol();
//         }).then((symbol) => {
//             assert.equal(symbol, "STNK", "has correct symbol");
//             return tokenInstance.standard();
//         }).then((standard) => {
//             assert.equal(standard, "STNK Token v1.0", "has correct standard");
//         })
//     })

//     it("allocates the initial supply upon deployment", () => {
//         return StkToken.deployed().then((instance) => {
//             tokenInstance = instance;
            
//             return tokenInstance.totalSupply();
//         }).then((totalSupply) => {
//             // check if supply equal to value we expect
//             assert.equal(totalSupply.toNumber(), 1000000000000000000000000, 
//                         "sets total supply to 1,000,000");
            
//             return tokenInstance.balanceOf(accounts[0]);
//         }).then((adminBalance) => {
//             assert.equal(adminBalance.toNumber(), 1000000000000000000000000, 
//                         "allocates initial supply to admin account");
//         })
//     })

//     it("transfer token ownership", () => {
//         return StkToken.deployed().then((instance) => {
//             tokenInstance = instance;

//             // test require statement first by transferring something larger than sender's balance
//             // call - call funcs and inspect (doesnt actually do a transaction, just testing)
//             return tokenInstance.transfer.call(accounts[1], 999999999999999);
//         }).then(assert.fail).catch((err) => {
//             // console.log("YES" + err.message);
//             assert(err.message.indexOf("revert") >= 0, "error must contain revert");

//             return tokenInstance.transfer.call(accounts[1], 250000, { from: accounts[0]});
//         }).then((success) => {
//             assert.equal(success, true, "transfer func returned true");

//             // this transfer has no call so its a transaction (means u receive a receipt)
//             // see more at truffle docs for transaction VS calls
//             return tokenInstance.transfer(accounts[1], 250000, {from: accounts[0]});
//         }).then((receipt) => {
//             // receipt has logs
//             assert.equal(receipt.logs.length, 1, 'triggers one event');
//             // make sure is transfer event
//             assert.equal(receipt.logs[0].event, 'Transfer', 'should be the "Transfer" event');
//             // make sure event has all arguments who looking for
//             assert.equal(receipt.logs[0].args._from, accounts[0], 'logs the account the tokens are transferred from');
//             assert.equal(receipt.logs[0].args._to, accounts[1], 'logs the account the tokens are transferred to');
//             assert.equal(receipt.logs[0].args._value, 250000, 'logs the transfer amount');

//             return tokenInstance.balanceOf(accounts[1]);
//         }).then((balance) => {
//             assert.equal(balance.toNumber(), 250000, "amount added to receiving account");

//             return tokenInstance.balanceOf(accounts[0]);
//         }).then((balance) => {
//             assert.equal(balance.toNumber(), 750000, "amount deducted from sending account");
//         })
//     })

//     it("approves tokens for delegated transfer", () => {
//         return StkToken.deployed().then((instance) => {
//             tokenInstance = instance;
//             return tokenInstance.approve.call(accounts[1], 100);
//         }).then((success) => {
//             assert.equal(success, true, "approve returns true");

//             return tokenInstance.approve(accounts[1], 100, {from: accounts[0]});
//         }).then((receipt) => {
//             // receipt has logs
//             assert.equal(receipt.logs.length, 1, 'triggers one event');
//             // make sure is transfer event
//             assert.equal(receipt.logs[0].event, 'Approval', 'should be the "Approval" event');
//             // make sure event has all arguments who looking for
//             assert.equal(receipt.logs[0].args._owner, accounts[0], 'logs the account the tokens are authorised by');
//             assert.equal(receipt.logs[0].args._spender, accounts[1], 'logs the account the tokens are authorised to');
//             assert.equal(receipt.logs[0].args._value, 100, 'logs the transfer amount');

//             return tokenInstance.allowance(accounts[0], accounts[1]);
//         }).then((allowance) => {
//             assert.equal(allowance.toNumber(), 100, "stores allowance for delegated transfers");
//         })
//     });

//     it("handles delegated token transfers", () => {
//         return StkToken.deployed().then((instance) => {
//             tokenInstance = instance;

//             fromAcc = accounts[2];
//             toAcc = accounts[3];
//             spendingAcc = accounts[4];

//             // transfer some tokens to fromAcc so we can test
//             return tokenInstance.transfer(fromAcc, 100, {from: accounts[0]})
//         }).then((receipt) => {
//             // approve spendingAcc to spend 10 tokens from fromAcc
//             return tokenInstance.approve(spendingAcc, 10, {from: fromAcc});
//         }).then((receipt) => {
//             // try transferring sth larger than sender's balance
//             return tokenInstance.transferFrom(fromAcc, toAcc, 999, {from: spendingAcc});
//         }).then(assert.fail).catch((err) => {
//             assert(err.message.indexOf("revert") >= 0, "cannot transfer value larger than balance");

//             // try transferring sth larger than approved amt (allowance)
//             return tokenInstance.transferFrom(fromAcc, toAcc, 20, {from: spendingAcc});
//         }).then(assert.fail).catch((err) => {
//             assert(err.message.indexOf("revert") >= 0, "cannot transfer value larger than allowance");

//             return tokenInstance.transferFrom.call(fromAcc, toAcc, 10, {from: spendingAcc});
//         }).then((success) => {
//             assert.equal(success, true);

//             // create actual transaction (no call) - will change state of blockchain
//             return tokenInstance.transferFrom(fromAcc, toAcc, 10, {from: spendingAcc});
//         }).then((receipt) => {
//             // receipt has logs
//             assert.equal(receipt.logs.length, 1, 'triggers one event');
//             // make sure is transfer event
//             assert.equal(receipt.logs[0].event, 'Transfer', 'should be the "Transfer" event');
//             // make sure event has all arguments who looking for
//             assert.equal(receipt.logs[0].args._from, fromAcc, 'logs the account the tokens are tramsferred from');
//             assert.equal(receipt.logs[0].args._to, toAcc, 'logs the account the tokens are transferred to');
//             assert.equal(receipt.logs[0].args._value, 10, 'logs the transfer amount');

//             return tokenInstance.balanceOf(fromAcc);
//         }).then((balance) => {
//             assert.equal(balance.toNumber(), 90, "amt deducted from sending account");

//             return tokenInstance.balanceOf(toAcc);
//         }).then((balance) => {
//             assert.equal(balance.toNumber(), 10, "amt added to receiving account");

//             return tokenInstance.allowance(fromAcc, spendingAcc);
//         }).then((allowance) => {
//             assert.equal(allowance.toNumber(), 0, "deducts amount from allowance");
//         })
//     })
// })