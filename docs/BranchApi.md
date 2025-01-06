# Branch API Documentation

## 1. Add Branch With Valid Data

**Endpoint:**
POST http://localhost:8080/api/branch

**Request Body:**
```json
{
  "branchCode": "0204",
  "branchName": "Kuningan Tengah",
  "address": "Jl. Kuningan Tengah No.XX",
  "phoneNumber": "081234512312"
}
```
Response:
```json
{
  "branchId": "generated-branch-id",
  "branchCode": "0204",
  "branchName": "Kuningan Tengah",
  "address": "Jl. Kuningan Tengah No.XX",
  "phoneNumber": "081234512312",
  "createdAt": "2025-01-04T07:58:16.000Z",
  "updatedAt": "2025-01-04T07:58:16.000Z"
}
```

## 2. Get Branch By Id
**Endpoint:**
GET http://localhost:8080/api/branch/{branchId}

**Request URL Example:**
GET http://localhost:8080/api/branch/xxx

Response:
```json
{
  "branchId": "xxx",
  "branchCode": "0204",
  "branchName": "Kuningan Tengah",
  "address": "Jl. Kuningan Tengah No.XX",
  "phoneNumber": "081234512312",
  "createdAt": "2025-01-04T07:58:16.000Z",
  "updatedAt": "2025-01-04T07:58:16.000Z"
}
```

## 3. Update Branch With Valid Data
**Endpoint:**
PUT http://localhost:8080/api/branch
**Request Body:**
```json
{
  "branchId": "xxx",
  "branchCode": "0204",
  "branchName": "Kuningan Tengah",
  "address": "Jl. Kuningan Tengah No.XX",
  "phoneNumber": "081234512312"
}
```

**Response:**
```json
{
  "branchId": "xxx",
  "branchCode": "0204",
  "branchName": "Kuningan Tengah",
  "address": "Jl. Kuningan Tengah No.XX",
  "phoneNumber": "081234512312",
  "createdAt": "2025-01-04T07:58:16.000Z",
  "updatedAt": "2025-01-04T07:58:16.000Z"
}
```

## 4. Delete Branch By Id
**Endpoint:**
DELETE http://localhost:8080/api/branch/{branchId}
**Request URL Example:**
DELETE http://localhost:8080/api/branch/xxx

**Response:**
```json
{
  "message": "Branch deleted successfully"
}
```

## 5. Get All Branches
**Endpoint:**
GET http://localhost:8080/api/branch

**Response:**
```json
[
  {
    "branchId": "xxx",
    "branchCode": "0204",
    "branchName": "Kuningan Tengah",
    "address": "Jl. Kuningan Tengah No.XX",
    "phoneNumber": "081234512312",
    "createdAt": "2025-01-04T07:58:16.000Z",
    "updatedAt": "2025-01-04T07:58:16.000Z"
  },
  {
    "branchId": "yyy",
    "branchCode": "0205",
    "branchName": "Kuningan Barat",
    "address": "Jl. Kuningan Barat No.XX",
    "phoneNumber": "081234512313",
    "createdAt": "2025-01-04T07:58:16.000Z",
    "updatedAt": "2025-01-04T07:58:16.000Z"
  }
]
```

## 6. Get Branch By Branch Code
**Endpoint:**
GET http://localhost:8080/api/branch?branchCode=xxx&branchName=xxx&page=0&size=10
**Request URL Example:**
GET http://localhost:8080/api/branch?branchCode=0204&branchName=Kuningan&page=0&size=10

**Response:**
```json
[
  {
    "branchId": "xxx",
    "branchCode": "0204",
    "branchName": "Kuningan Tengah",
    "address": "Jl. Kuningan Tengah No.XX",
    "phoneNumber": "081234512312",
    "createdAt": "2025-01-04T07:58:16.000Z",
    "updatedAt": "2025-01-04T08:10:00.000Z"
  }
]
```