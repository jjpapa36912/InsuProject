import React, {useEffect, useState} from 'react';
import {Link, useNavigate} from 'react-router-dom';
import axios from "axios";
import {Button, ButtonGroup, Table} from "react-bootstrap";

const BoardList = () => {
  const [boardList, setBoardList] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get('/boardList');
        setBoardList(response.data);
      } catch (error) {
        console.log('Error fetching OrderEntity: ', error);
      }

    }
    fetchData();

  }, []);

  const handleItemClick = (id) => {
    navigate('/boardDetailView/' + id);
  }

  const handleEditClick = (id) => {
    navigate('/boardEditView/' + id);
  }

  return (<div>
    <Table className="mt-4">
      <thead>
      <tr>
        <th width="30%">Name</th>
        <th width="30%">Title</th>
        <th width="40%">Actions</th>
      </tr>
      </thead>
      <tbody>
      {boardList.map(data => (
          <tr key={data.id}>
            <td style={{whiteSpace: 'nowrap'}}>{data.userId}</td>
            <td button onClick={() => handleItemClick(data.id)}>
              {/*<BoardDetailView data={selectedItem} />*/}
              <a href="">{data.orderTitle}</a>
            </td>
            <td>
              <ButtonGroup>
                <Button size="sm" color="primary"
                        onClick={() => handleEditClick(data.id)}>Edit</Button>
                <Button size="sm" color="danger"
                        onClick={() => this.remove(
                            data.id)}>Delete</Button>
              </ButtonGroup>
            </td>
          </tr>
      ))}
      </ tbody>
    </Table>
  </div>)
}
export default BoardList;