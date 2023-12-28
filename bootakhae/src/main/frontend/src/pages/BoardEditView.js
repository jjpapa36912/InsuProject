import React, {useEffect, useState} from 'react';
import {useNavigate, useParams} from "react-router-dom";
import axios from "axios";
import {Button, ButtonGroup} from "react-bootstrap";

const BoardEditView = () => {
  const orderId = useParams();
  const [orderEntity, setOrderEntity] = useState();
  const [id, setId] = useState();
  const [orderTitle, setOrderTitle] = useState();
  const [orderContents, setOrderContents] = useState();
  const navigate = useNavigate();

  useEffect(() => {
    axios.post('/boardItem/' + orderId.id)
    .then(response => {
      setOrderEntity(response);
      setId(response.data.id);
      setOrderTitle(response.data.orderTitle);
      setOrderContents(response.data.orderContents);
    });
  }, [])

  const handleEditCompleteClick = async () => {
    try {
      const response = await axios.post('/updateOrder', {
        id,
        orderTitle,
        orderContents,
      });
      if (response.status === 200) {
        navigate('/boardList')
        console.log('Edit order successful');
      }
    } catch (error) {
      console.log('Failed to save edited order data!', error);
    }
  }

  return (
      <div>
        <h2>Detail View</h2>
        {orderEntity && ( // Check if item is truthy before rendering
            <div key={orderEntity.id}>
              {/*<div contentEditable="true">Title: {orderEntity.orderTitle}</div>*/}
              <div><p>Title: </p><textarea
                  value={orderTitle}
                  onChange={(e) => setOrderTitle(e.target.value)}
                  rows={1} // Specify the number of visible rows
                  cols={50} // Specify the number of visible columns
              />
              </div>
              <p>Description: </p><textarea
                value={orderContents}
                onChange={(e) => setOrderContents(e.target.value)}
                rows={10} // Specify the number of visible rows
                cols={50} // Specify the number of visible columns
            />
              <hr/>
              <ButtonGroup>
                <Button size="sm" color="primary"
                        onClick={() => handleEditCompleteClick()}>완료</Button>
              </ButtonGroup>
            </div>
        )}
      </div>
  );
};

export default BoardEditView;