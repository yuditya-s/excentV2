<?php

// header("Content-Type: application/json; charset=UTF-8");

require_once 'koneksi.php';

if($_SERVER['REQUEST_METHOD'] == 'POST'){

	$id_user = $_POST['id_user'];
	$email = $_POST['email'];
	$password = $_POST['password'];
	$nama_lengkap = $_POST['nama_lengkap'];
	$status = $_POST['status'];
	$nip_nis = $_POST['nip_nis'];
	$kelas = $_POST ['kelas'];
	$gender = $_POST['gender'];
	$no_hp = $_POST['no_hp'];
	$img_user = $_POST['img_user'];

	$perintah = "UPDATE table_pengguna SET 
	email='$email',
	password='$password',
	nama_lengkap='$nama_lengkap',
	status='$status',
	nip_nis='$nip_nis',
	kelas='$kelas',
	gender='$gender',
	no_hp='$no_hp'
	WHERE id_user = '$id_user' ";

	if(mysqli_query($conn, $perintah)){
		if($img_user == null){
			$result["value"] = "1";
			$result["message"] = "Success";

			echo json_encode($result);
			mysqli_close($conn);
		} else {
			// path adalah lokasi relatif dari file .php yang beroperasi dibaca oleh file_put_contents
			// finalpath adalah lokasi lengkap DARI alamat host yang nantinya akan diload oleh komponen penampil gambar	
			$path = "img-user/$id_user.jpeg";
			$finalpath = "/data-pengguna/".$path;

			$insert_image = "UPDATE table_pengguna SET img_user = '$finalpath' WHERE id_user = '$id_user'";

			if(mysqli_query($conn, $insert_image)){
				if(file_put_contents($path, base64_decode($img_user))){
					$result["value"] = "1";
					$result["message"] = "Success";
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
	} 

} else {
		$response["value"] = "0";
	$response["message"] = "Tidak ada post data";
	echo json_encode($response);
	mysqli_close($conn);
	}
?>	