<?php

include 'config.php';
$con = mysqli_connect(HOST,USER,PASS,DB);


if($_SERVER['REQUEST_METHOD']=='POST'){
   $json = file_get_contents('php://input');
   $obj = json_decode($json);
   $platenumber = $obj->{'plateNumber'};

   $select_sql = "SELECT * FROM registered_vehicles  WHERE Plate_Number ='".$platenumber."' ";
   $resulti = mysqli_query($con,$select_sql);

   if(mysqli_num_rows($resulti) >0){
     $delete_sql = "DELETE FROM registered_vehicles  WHERE Plate_Number ='".$platenumber."' ";
     $delete = mysqli_query($con,$delete_sql);
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