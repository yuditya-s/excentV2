<?php
require("koneksi.php");

$response= array();

if($_SERVER['REQUEST_METHOD'] == 'POST'){

	$email = $_POST['email'];
	$password = $_POST['password'];
	$nama_lengkap = $_POST['nama_lengkap'];
	$status = $_POST['status'];
	$nip_nis = $_POST['nip_nis'];
	$kelas = $_POST['kelas'];
	$gender = $_POST['gender'];
	$no_hp = $_POST['no_hp'];
	$img_user = $_POST['img_user'];


	$perintah = "INSERT INTO table_pengguna (email, password, nama_lengkap, status, nip_nis, kelas, gender, no_hp) VALUES ('$email', '$password','$nama_lengkap','$status', '$nip_nis','$kelas','$gender', '$no_hp')";
	
	$eksekusi = mysqli_query($conn, $perintah);
	$cek = mysqli_affected_rows($conn);

	if($cek > 0){
		if($img_user == null){

		$response["value"] = "1";
        $response["message"] = "Success!";
     	echo json_encode($response);
        mysqli_close($conn);

		} else {
			$id = mysqli_insert_id($conn);
			$path = "img-user/$id.jpeg";
			$finalpath = "data-pengguna/".$path;
			$insert_img = "UPDATE table_pengguna SET img_user = '$finalpath' WHERE id_user='$id'";

			if(mysqli_query($conn, $insert_img)){
				if(file_put_contents($path, base64_decode($img_user))){
					$result["value"] = "1";
                    $result["message"] = "Success!";
            
                    echo json_encode($result);
                    mysqli_close($conn);
				} else {
					$response["value"] = "0";
                    $response["message"] = "Error! ".mysqli_error($conn);
                    echo json_encode($response);
					mysqli_close($conn);
				}
			}
		}
		
	} else {
		$response["value"] = "0";
		$response["message"] = "Error! ".mysqli_error($conn);
		echo json_encode($response);
		mysqli_close($conn);
	}
} else {
	$response["value"] = "0";
	$response["message"] = "Tidak ada post data";
	echo json_encode($response);
	mysqli_close($conn);
}
?>