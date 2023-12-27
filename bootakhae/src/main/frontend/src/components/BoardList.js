import React, {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import axios from "axios";
import {Button, ButtonGroup, Table} from "react-bootstrap";

const BoardList = () => {
  const [boardList, setBoardList] = useState([]);
  const [memberEntity, setMemberEntity] = useState([]);
  let [orderEntity, setOrderEntity] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
      await axios.get('/boardList')
      .then(response => {
        const fetchedData = Object.values(response.data);
        setBoardList(fetchedData);

        // Object.values(boardList).map((data)=>{
        //   console.log(data.name+"<<<<<")
        // })
        // console.log("111");

      }).catch(error=>{
        console.log("Failed to fetch Data for board list" + error);
      });
    }
      // try {
      //   if (response.data) {
      //     setBoardList(response.data);
      //     // const prototypeOfObject = Object.getPrototypeOf(response.data);
      //     // setOrderEntity(prototypeOfObject);
      //     Object.entries(response.data).map((value) => {
      //       // console.log("key : " + key);
      //       // value[1].orderEntities[0].orderTitle
      //
      //       // value = > array(2) : 0번째에는 키값인 1이들어있고, 1번째에 memberEntity가 들어있음. memberEntity에는 orderEntities가 들어있고 그것도 마찬
      //       // 가지로 array(1) : 이건 바로 orderEntity가 들어있음
      //       // setMemberEntity(value[1]);
      //       // setOrderEntity(Object.getPrototypeOf(value[1].orderEntities[0]));
      //       console.log(memberEntity.name);
      //
      //     })
      //     Object.entries(orderEntity).map((order) => {
      //       console.log(order.orderTitle);
      //     })
      //
      //   }
      //   // Object.entries(memberEntity).map((memberData) =>{
      //   //   console.log(memberData.name)
      //   // })
      //   // setOrderEntity(value[1].orderEntities[0]);
      //   //   console.log("wsefsdf>>> " + memberEntity.name);
      //   //   console.log("heyley!!!!!" + orderEntity.orderTitle);
      //
      // } catch (error) {
      //   console.log('Error fetching OrderEntity: ', error);
      // }
    fetchData();
  }, []);

  const handleItemClick = (id) => {
    navigate('/boardDetailView/' + id);
  }

  const handleEditClick = (id) => {
    navigate('/boardEditView/' + id);
  }
  const handleWriteClick = () => {
    navigate('/boardWriteOrderView');
  }

  return <div>
    <ButtonGroup>
      <Button color="primary" onClick={handleWriteClick}>글쓰기</Button>
    </ButtonGroup>
    <Table className="mt-4">
      <thead>
      <tr>
        <th width="30%">Name</th>
        <th width="30%">Title</th>
        <th width="40%">Actions</th>
      </tr>
      </thead>
      <tbody>
      {boardList && boardList.map((value) => (
          Object.values(value.orderEntities).map((order)=>(
              <tr key={value.id}>
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
                            onClick={() => this.remove(
                                value.id)}>Delete</Button>
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