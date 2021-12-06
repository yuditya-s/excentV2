<?php
header("Content-Type: application/json; charset=UTF-8");

require_once 'koneksi.php';

$query = "SELECT id_ec, name_ec, id_user FROM table_ec ORDER BY id_ec ASC";

$result = mysqli_query($conn, $query);
$response = array();

while($row = mysqli_fetch_assoc($result)){
	$id_user = $row['id_user'];
	$query2 = "SELECT nama_lengkap FROM table_pengguna WHERE id_user= '$id_user'";
	$result2 = mysqli_query($conn, $query2);
	$row2 = mysqli_fetch_assoc($result2);

	array_push($response, 
		array(
			'id_ec' => $row['id_ec'],
			'name_ec' => $row['name_ec'],
			'id_user' => $row['id_user'],
			'pembina_ec' => $row2['nama_lengkap']
			)
		);
}

echo json_encode($response);
mysqli_close($conn);
?>