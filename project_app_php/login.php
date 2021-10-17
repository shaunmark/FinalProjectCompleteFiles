<?php

include 'config.php';
$con = mysqli_connect(HOST,USER,PASS,DB);


if($_SERVER['REQUEST_METHOD']=='POST'){
   $json = file_get_contents('php://input');
   $obj = json_decode($json);
   $username = $obj->{'username'};
   $password = $obj->{'password'};

 //$username = '';
 //$password = '';

   $sqli = "SELECT * FROM app_login WHERE app_username='".$username."' AND app_password='".$password."'";
   $resulti = mysqli_query($con,$sqli);

   if($row=mysqli_fetch_array($resulti)){

     $name["response"]="True";
     echo json_encode($name);
   }
   else {
     $name["response"]="False";
     echo json_encode($name);
   }

 }else{
   $name["success"]="failure";
   echo json_encode($name);
 }
 mysqli_close($con);
?>
