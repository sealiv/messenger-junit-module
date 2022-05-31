Feature: MailServer test

  Scenario: Test partition Mock for method 'send' and origin method 'sendConsole'
    Given Create partition MailServer Mock with mocked method 'send'
    When mock method should return "out to file" when it call
    Then check call original method with 2 parameters: "a", "b" should return "console"
    And check call mock method when 3 parameters: "a", "b", "c" should return "out to file"