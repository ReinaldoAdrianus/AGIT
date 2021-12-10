// Customer List
fetch("https://gorest.co.in/public/v1/users").then(
  res => {
    res.json().then(
      data => {
        console.log(data.data);
        // Display Data Index
        if (data.data.length > 0) {
            var list = "";
            data.data.forEach((customer) => {
                list += "<tr>";
                list += "<td>" + customer.name + "</td>";
                list += "<td>" + customer.status + "</td>";
                list += '<td><button type="click" class="viewbutton"><a href="detail.html">view customer</a></button></td></tr>';
            });
          document.getElementById('data').innerHTML = list;
        }
      }
    )
  }
)

// Customer Details
let viewButton = document.querySelector('.viewbutton');
viewButton.forEach(btn => {
  btn.addEventListener('click', function(){
    const id = this.data.id;
    async function getData() {
      const response = await fetch("https://gorest.co.in/public/v1/users"+id);
      const respJson = await response.json();
      const {data} = respJson;
      const { name, status, email, gender } = data[0];
      
      document.getElementById('content-1-fn').innerHTML = name;
      document.getElementById('content-1-gender').innerHTML = gender;
      document.getElementById('content-1-email').innerHTML = email;
      document.getElementById('content-2-status').innerHTML = status;
      
    }
    getData();
  })
})



