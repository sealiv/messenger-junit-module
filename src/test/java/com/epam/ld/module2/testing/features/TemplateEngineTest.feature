Feature: TemplateEngine test

  Scenario: Test change all #tags to their values
    Given Create template with tags "TEMPLATE_WITH_ONE_TAG"
    Given Create Client with email "first@mail.com"
    Then should find email in template after generateMessage

  Scenario: TemplateEngine must throw exception if left raw tags
    Given Create template with tags "TEMPLATE_WITH_THREE_TAGS"
    Given Create Client with email "second@mail.com"
    When do nothing when application try to find tags and to change its to their values
    Then should throw exception when raw tags is present

  Scenario: TemplateEngine must process without exception if was passed more parameters than exists raw tags
    Given Create template with tags "TEMPLATE_WITH_THREE_TAGS"
    Given Create Client with email "threeth@mail.com"
    When do nothing when application try to find tags and to change its to their values
    When pass more parameters than exists raw tags
    Then should process without exception when was passed more parameters than exists raw tags

  Scenario: Write file pseudo-parametrized
    Given We have three file with templates
      | test_template_1.txt |
      | test_template_2.txt |
      | test_template_3.txt |
    Then should find tag "#{subject}" in each template