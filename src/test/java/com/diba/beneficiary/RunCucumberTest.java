//package com.diba.beneficiary;
//
//import io.cucumber.junit.Cucumber;
//import org.junit.jupiter.api.Test;
//import org.junit.platform.suite.api.ConfigurationParameter;
//import org.junit.platform.suite.api.IncludeEngines;
//import org.junit.platform.suite.api.SelectClasspathResource;
//import org.junit.platform.suite.api.Suite;
//import org.junit.runner.RunWith;
//
//import java.io.File;
//
//import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
//import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;
//import static org.junit.Assert.assertTrue;
//
//@Suite
//@RunWith(Cucumber.class)
//@SelectClasspathResource("features")
//@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
//@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "usage")
//@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "html:target/cucumber-reports.html")
//@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.diba.beneficiary.glue")
//class RunCucumberTest {
//    @Test
//    void testSuite() {
//        File bddResourcesDirectory = new File("src/test/resources/features");
//        assertTrue(bddResourcesDirectory.exists());
//    }
//}