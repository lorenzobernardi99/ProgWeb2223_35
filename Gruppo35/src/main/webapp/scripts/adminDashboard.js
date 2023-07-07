// Extract JSESSIONID from URL
function getJSessionIdFromUrl() {
      const referrerUrl = document.referrer;
      let jsessionId = "";

      const regex = /(;)jsessionid=([^&]+)/;
      let match = regex.exec(referrerUrl);
      if (match) {
            jsessionId = match[0];
      }
      return jsessionId;
}

// method to process response status
function processStatus(response) {
      const ok = 200;

      if (response.redirected){
          window.location.href = response.url;
      }
      if (response.status === ok) {
            return Promise.resolve(response)
      }
      else {
            return Promise.reject(response.status)
      }
}
// method that retrieves user data
function retrieveUsers(filter){
      // Preparing request
      let url = "GetUsers" + getJSessionIdFromUrl() + "?filter=" + filter;

      // Making request
      fetch(url)
          .then(processStatus)
          .then(function (response){ return response.json(); })
          .then(function (response){
                  // Getting returned array of cities
                  let my_JSON_array = response;

                  // Finding table to fill in
                  let table = document.getElementById("users");

                  // Removing old table if existing and hiding it
                  while (table.childNodes.length) {
                        table.removeChild(table.childNodes[0]);
                  }

                  if (my_JSON_array.length > 0) {

                        // Creating table header
                        let thead = table.createTHead();
                        let row = thead.insertRow();
                        let header = ["Nome", "Cognome", "Data di nascita", "E-mail", "Numero di telefono", "Ruolo", "Username", "Password",];
                        for (let key of header) {
                              let th = document.createElement("th");
                              let text = document.createTextNode(key);
                              th.appendChild(text);
                              row.appendChild(th);
                        }

                        // Creating table rows
                        for (let i = 0; i < my_JSON_array.length; i++) {
                              row = table.insertRow();
                              let current_JSON_object  = JSON.parse(my_JSON_array[i]);
                              for (let key in current_JSON_object) {
                                    if(key === "id")
                                          continue;
                                    let cell = row.insertCell();
                                    let text = document.createTextNode(current_JSON_object[key]);
                                    cell.appendChild(text);
                              }
                        }

                  }
                  else {
                        // Displaying error if the list is empty
                        let row = table.insertRow();
                        let cell = row.insertCell();
                        let text = document.createTextNode("Nessun utente trovato");
                        cell.appendChild(text);
                  }
          })
          .catch(error => {
                document.getElementById("errorText").innerHTML = "Error " + error
                document.getElementById("errorText").classList.add("error-visible");
          });
}
// method that retrieves data to populate views chart
function viewsPerPage(){
      const keyMap = {
            'total':'Totale',
            'homepage':'Homepage',
            'whoWeAre':'Chi siamo',
            'activities':'Attività',
            'contactUs':'Contatti',
            'signIn':'SignIn',
            'login':'Login'
      };
      let data = [];

      // Preparing request
      let url = "GetViews" + getJSessionIdFromUrl();

      // Making request
      fetch(url)
          .then(processStatus)
          .then(function (response){ return response.json(); })
          .then(function (response){
                // Getting returned array of views
                let my_JSON_array = response;

                // Finding total element
                let total = document.getElementById("total");

                // getting json data

                // Compose data array
                for (let i = 0; i < my_JSON_array.length; i++) {
                      let current_JSON_object  = JSON.parse(my_JSON_array[i]);
                      for (let key in current_JSON_object) {
                            const mappedKey = keyMap[key];
                            data.push([mappedKey, current_JSON_object[key]]);
                      }
                }
                // Insert total of views
                total.innerText = "Totale delle visite: " + data[0][1];
                // remove total views from the data, it isn't visible in the chart
                data.splice(0, 1);
                // Column chart
                const chart = Highcharts.chart('chart', {
                      chart: {
                            type: 'column'
                      },
                      title: {
                            text: 'Numero di visite per pagina'
                      },
                      xAxis: {
                            type: 'category',
                            title: {
                                  text: 'Pagine'
                            }
                      },
                      yAxis: {
                            title: {
                                  text: 'Numero di visite'
                            }
                      },
                      series: [{
                            name: 'Visite',
                            data: data
                      }]
                });
                document.getElementById("total").style.display = 'block';
                document.getElementById("reset-button").style.display = 'block';
                document.getElementById("chart").style.display = 'block';
          })
          .catch(error => {
                document.getElementById("errorText").innerHTML = "Error " + error
                document.getElementById("errorText").classList.add("error-visible");
          });
      }
// method that calls reset of view's counter
function resetViews(fieldToReset) {
      let url = "ResetViews" + getJSessionIdFromUrl() + "?field=" + fieldToReset;

      // Making request
      fetch(url)
          .then(processStatus)
          .then(viewsPerPage)
          .catch(error => {
                document.getElementById("errorText").innerHTML = "Error " + error
                document.getElementById("errorText").classList.add("error-visible");
          });
}
// method that retrieves data to populate donation chart
function donationReceived(){
      const Months = {
            1: 'gennaio',
            2: 'febbraio',
            3: 'marzo',
            4: 'aprile',
            5: 'maggio',
            6: 'giugno',
            7: 'luglio',
            8: 'agosto',
            9: 'settembre',
            10: 'ottobre',
            11: 'novembre',
            12: 'dicembre'
      };
      let data = [];

      // Preparing request
      let url = "GetDonations" + getJSessionIdFromUrl();
      // Making request
      fetch(url)
          .then(processStatus)
          .then(function (response){ return response.json(); })
          .then(function (response){
                  // Getting returned array of views
                  let my_JSON_array = response;
                  // Compose data array
                  for (let i = 0; i < my_JSON_array.length; i++) {
                        let current_JSON_object  = JSON.parse(my_JSON_array[i]);
                        for (let key in current_JSON_object) {
                              data.push([Months[key], current_JSON_object[key]]);
                        }
                  }

                  let year = new Date().getFullYear();

                  // Column chart
                  const chart = Highcharts.chart('donationChart', {
                        chart: {
                              type: 'line'
                        },
                        title: {
                              text: year
                        },
                        xAxis: {
                              type: 'category',
                              title: {
                                    text: 'Mesi'
                              }
                        },
                        yAxis: {
                              title: {
                                    text: 'Donazioni'
                              }
                        },
                        series: [{
                              name: 'Donazioni',
                              data: data
                        }]
                  });
                document.getElementById("donationChart").style.display = 'block';
          })
          .catch(error => {
                document.getElementById("errorText").innerHTML = "Error " + error
                document.getElementById("errorText").classList.add("error-visible");
          });
}