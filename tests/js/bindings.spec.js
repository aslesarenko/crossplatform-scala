const {
  run
} = require("../../core/js/target/scala-2.13/core-test-fastopt/main.js");

describe("Smoke tests for generated JS", () => {
  it("Should have method", () => {
    run()
  });
});
