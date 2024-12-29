#!/bin/bash

# Wait for MongoDB to be ready
until mongosh --eval "print('waiting for connection')" &>/dev/null; do
  sleep 2
done

# Initialize the replica set
mongosh <<EOF
rs.initiate({
  _id: "myReplicaSet",
  members: [
    { _id: 0, host: "mongo1:27017" },
    { _id: 1, host: "mongo2:27017" },
    { _id: 2, host: "mongo3:27017" }
  ]
})
EOF
