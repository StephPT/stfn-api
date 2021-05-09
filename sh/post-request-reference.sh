curl --header "Content-Type: application/json" --request POST --data '{
  "uuid": "8855d310-9a2a-11eb-aa2f-cd03a6361dee",
  "name": "Another Reference",
  "fields": [
    {
      "uuid": "8855d310-9a2a-11eb-aa2f-cd03a6361dee",
      "options": {
        "name": "Volume"
      }
    },
    {
      "uuid": "8855d310-9a2a-11eb-aa2f-cd03a6361dee",
      "options": {
        "name": "Url"
      }
    },
    {
      "uuid": "8855d310-9a2a-11eb-aa2f-cd03a6361dee",
      "options": {
        "name": "Issue"
      }
    }
  ],
  "example": "<em>Online Abertillery</em> (2010) Available at: http://www.abertillery.net/tales_ghost.html (Accessed: 19 July 2010)."
}' http://localhost:8080/test/save