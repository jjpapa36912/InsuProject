import React, {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import axios from "axios";
import {Button, ButtonGroup, Table} from "react-bootstrap";
import {MEMBER_ENTITY_USER_ID_SESSION} from "../layout/Header";

const BoardList = () => {
  const [boardList, setBoardList] = useState([]);
  const navigate = useNavigate();
  console.log("KKKKKKKK"+sessionStorage.getItem(MEMBER_ENTITY_USER_ID_SESSION))

  useEffect(() => {
    const fetchData = async () => {
      await axios.get('/boardList')
      .then(response => {
        const fetchedData = Object.values(response.data);
        setBoardList(fetchedData);
      }).catch(error=>{
        console.log("Failed to fetch Data for board list" + error);
      });
    }

    fetchData();
  }, []);

  const handleItemClick = (id) => {
    navigate('/boardDetailView/' + id);
  }

  const handleEditClick = (id) => {
    navigate('/boardEditView/' + id);
  }
  const handleWriteClick = () => {
    navigate('/boardRegisterOrderView');
  }
  const handleDeleteClick = async (id) => {
    console.log(">>>" + id);
    await axios.post("/deleteOrder", {
      id
    }).then(response => {
      if (response.status == 200){
        console.log("Succeeded to delete order")
        navigate("/boardList")
      }
    })
  }

  return <div>
    <ButtonGroup>
      <Button color="primary" onClick={handleWriteClick}>글쓰기</Button>
    </ButtonGroup>
    <Table className="mt-4">
      <thead>
      <tr>
        <th width="30%">No.</th>
        <th width="30%">Name</th>
        <th width="30%">Title</th>
        <th width="40%">Actions</th>
      </tr>
      </thead>
      <tbody>
      {boardList && boardList.map((value) => (
          Object.values(value.orderEntities).map((order)=>(
              <tr key={value.id}>
                <td style={{whiteSpace: 'nowrap'}}>{value.id}</td>
                <td style={{whiteSpace: 'nowrap'}}>{value.name}</td>
                <td button onClick={() => handleItemClick(
                    value.id)}>
                  <a href="">{order.orderTitle}</a>
                </td>
                <td>
                  <ButtonGroup>
                    <Button size="sm" color="primary"
                            onClick={() => handleEditClick(
                                value.id)}>Edit</Button>
                    <Button size="sm" color="danger"
                            onClick={() => handleDeleteClick(
                                order.id)}>Delete</Button>
                  </ButtonGroup>
                </td>
              </tr>
          ))
      ))
      }
      </ tbody>
    </Table>
  </div>
}
export default BoardList;