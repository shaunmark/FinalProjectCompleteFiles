<?php

include 'config.php';
$con = mysqli_connect(HOST,USER,PASS,DB);


 if($_SERVER['REQUEST_METHOD']=='POST'){

   $json = file_get_contents('php://input');
   $obj = json_decode($json);
   $firstname = $obj->{'firstName'};
   $lastname = $obj->{'lastName'};
   $platenumber = $obj->{'plateNumber'};
   $mobilenumber = $obj->{'mobileNumber'};

  //$sqli = "INSERT INTO student (ST_Username,ST_Firstname,ST_Lastname,ST_Password,ST_DOB,ST_Address) VALUES ('$username','$firstname','$lastname','$password','$emailid','$mobilenumber','$dob','$address')";
  $sqli = "INSERT INTO registered_vehicles (Plate_Number,First_Name,Last_Name,Phone_No)
                        VALUES ('$platenumber','$firstname','$lastname','$mobilenumber')";
  $resulti = mysqli_query($con,$sqli);
  if($resulti){
    $name["response"]="True";
    echo json_encode($name);
  }
  else{
    $name["response"]="False";
    echo json_encode($name);
  }

 }
 else{
   $name["response"]="Failure";
   echo json_encode($name);
 }
 mysqli_close($con);
?>
