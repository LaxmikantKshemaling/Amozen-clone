<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign Up Page</title>
<!-- Add Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
  body {
    background-color: #2e2e2e; /* Light black background */
    color: white;
  }
  .card {
    border-radius: 15px;
    background-color: #424242; /* Slightly darker card background */
  }
  .container {
    max-width: 800px; /* Increased width for alignment */
    margin-top: 10px; /* Reduced top margin */
  }
  section {
    min-height: 50vh; /* Decreased the section height */
  }
  .form-outline {
    margin-bottom: 8px; /* Reduced margin between form fields */
  }
  .btn-primary {
    width: 100%;
  }
  .img-fluid {
    max-height: 300px; /* Increased image height */
  }
  .card-body {
    padding: 10px; /* Padding for the card */
  }
  .h1 {
    font-size: 1.6rem; /* Smaller heading */
  }
  label {
    color: white; /* Ensure form labels are visible on the darker background */
  }
  .form-control {
    background-color: #555; /* Input field background to match the theme */
    color: white; /* Input text color */
  }
</style>
</head>
<body>
<section class="d-flex justify-content-center align-items-center">
  <div class="container">
    <div class="row d-flex justify-content-center align-items-center">
      <div class="col-lg-12 col-xl-11">
        <div class="card text-white">
          <div class="card-body p-md-5">
            <div class="row justify-content-center">
              <div class="col-md-6 col-lg-6 col-xl-5 order-2 order-lg-1">

                <p class="text-center h1 fw-bold mb-4 mt-3">Sign up</p>
<form id="signupForm" action="SignUp" method="POST">
    <div class="form-outline flex-fill mb-0">
        <input type="text" id="name" name="name" class="form-control" required />
        <label class="form-label" for="name">Name</label>
    </div>
    <div class="form-outline flex-fill mb-0">
        <input type="email" id="email" name="email" class="form-control" required />
        <label class="form-label" for="email">Email</label>
    </div>
    <div class="form-outline flex-fill mb-0">
        <input type="password" id="password" name="password" class="form-control" required />
        <label class="form-label" for="password">Password</label>
    </div>
    <div class="form-outline flex-fill mb-0">
        <input type="tel" id="number" name="number" class="form-control" required />
        <label class="form-label" for="number">Phone Number</label>
    </div>
    <button type="submit" class="btn btn-primary">Sign Up</button>
</form>


              </div>
              <div class="col-md-6 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">

                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp"
                  class="img-fluid" alt="Sample image">

              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

<!-- Add Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>

<!-- JavaScript to handle form submission -->
<script>
  function handleSignup(event) {
    event.preventDefault(); // Prevent default form submission

    // Show success alert
    alert('Registration successful! Redirecting to login page...');

    setTimeout(function() {
    	  window.location.href = '/Product.jsp'; // Corrected to the actual SignUp or Login page
    	}, 2000);


    return false; // Prevent further form submission
  }
</script>


<script>
  // Optionally handle alerting before submitting
  document.getElementById('signupForm').onsubmit = function(event) {
      event.preventDefault(); // Prevent default form submission
      alert('Registration successful! Redirecting to login page...');
      this.submit(); // Now proceed with the actual submission
  }
</script>

</body>
</html>
