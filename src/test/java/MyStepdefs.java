import com.diba.beneficiary.core.service.IMessageDispatcher;
import com.diba.beneficiary.shared.messages.command.Beneficiary.commands.CreateOne;
import com.diba.beneficiary.shared.messages.utils.Message;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MyStepdefs {


    private  IMessageDispatcher<Message> dispater ;


    @Given("a request to create a new beneficiary")
    public void aRequestToCreateANewBeneficiary() {
        var command = new CreateOne();
        dispater.dispatch(command);
    }

    @When("the createOne command is dispatched and if it is uniqe in the system")
    public void theCreateOneCommandIsDispatchedAndIfItIsUniqeInTheSystem() {
    }

    @Then("the beneficiary should be created successfully and get store in both eventstoreDB and mongoDB")
    public void theBeneficiaryShouldBeCreatedSuccessfullyAndGetStoreInBothEventstoreDBAndMongoDB() {

    }



}
