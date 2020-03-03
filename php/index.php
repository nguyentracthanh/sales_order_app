<?php
    
    require_once 'user.php';
	
    
    $username = "";
    
    $password = "";
    
    $email = "";
	
	$phone = "";

	$total="";
	
	$list_food="";
	
	
	if(isset($_POST['total'])){
        
        $total = $_POST['total'];
        
    }
	if(isset($_POST['list_food'])){
        
        $list_food = $_POST['list_food'];
        
    }
    
   if(isset($_POST['username'])){
        
        $username = $_POST['username'];
        
    }
    
    if(isset($_POST['password'])){
        
        $password = $_POST['password'];
        
    }
	if(isset($_POST['phone'])){
        
        $email = $_POST['phone'];
        
    }
    
    if(isset($_POST['email'])){
        
        $email = $_POST['email'];
        
    }
    
    
    $userObject = new User();
    
    // Registration
    
    if(!empty($username) && !empty($password) && !empty($email)){
        
        $hashed_password = md5($password);
        
        $json_registration = $userObject->createNewRegisterUser($username, $hashed_password, $email,$phone);
        
        echo json_encode($json_registration);
        
    }
    
    // Login
    
    if(!empty($username) && !empty($password) && empty($email)){
        
        $hashed_password = md5($password);
        
        $json_array = $userObject->loginUsers($username, $hashed_password);
        
        echo json_encode($json_array);
    }
	
	//request
	if(!empty($username) && !empty($list_food) && !empty($total)){
		$json_array=$userObject->cart($username);
		echo json_encode($json_array);
	}
	
	
    ?>