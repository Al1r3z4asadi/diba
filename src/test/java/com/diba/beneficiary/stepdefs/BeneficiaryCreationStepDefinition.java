package com.diba.beneficiary.stepdefs;

import com.diba.beneficiary.core.service.IMessageDispatcher;
import com.diba.beneficiary.infrastructure.esdb.IEventStoreDBRepository;
import com.diba.beneficiary.shared.messages.command.Beneficiary.commands.CreateOne;
import com.diba.beneficiary.shared.messages.utils.Message;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;


@SpringBootTest
public class BeneficiaryCreationStepDefinition {

    @Autowired
    private final IMessageDispatcher<Message> dispater ;

    private static GenericContainer generic = new GenericContainer();


    @BeforeAll
    public void init(){



    }

    public BeneficiaryCreationStepDefinition(IMessageDispatcher<Message> dispater) {
        this.dispater = dispater;
    }


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
