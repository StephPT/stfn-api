curl --header "Content-Type: application/json" --request POST --data '{
  "uuid": "5d8ab470-9a2a-11eb-aa2f-cd03a6361dee",
  "name": "Video Reference",
  "fields": [
    {
      "uuid": "5d8ab470-9a2a-11eb-aa2f-cd03a6361dee",
      "options": {
        "name": "Volume",
        "label": "Volume No.",
        "type": "number",
        "placeholder": "Test",
        "prefix": "",
        "suffix": " ",
        "italic": "1",
        "required": true
      }
    },
    {
      "uuid": "5d8ab470-9a2a-11eb-aa2f-cd03a6361dee",
      "options": {
        "name": "Issue",
        "label": "Issue Number",
        "type": "number",
        "placeholder": "A cool new placeholder",
        "prefix": "",
        "suffix": " ",
        "italic": "1",
        "required": false
      }
    },
    {
      "uuid": "5d8ab470-9a2a-11eb-aa2f-cd03a6361dee",
      "options": {
        "name": "Url",
        "label": "Available At",
        "type": "text",
        "placeholder": "Test",
        "prefix": "",
        "suffix": " ",
        "italic": "1",
        "required": true
      }
    }
  ],
  "example": "Example 3"
}' http://localhost:8080/test/save