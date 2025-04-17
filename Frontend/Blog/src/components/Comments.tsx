import SendIcon from "@mui/icons-material/Send";
import { Box, Paper, TextField, Typography } from "@mui/material";
import React from "react";
import CommentsListComponent from "./CommentList";
export interface Comment {
  //   id: string;
  //   author: string;
  //   content: string;
  //   avatarUrl: string;
  //   timestamp: string;
  id: string; // Changed from number to string
  content: string;
}

export interface CommentComponentProps {
  comments: Comment[];
  noCommentsToDisplay:number;
}

const CommentComponent: React.FC<CommentComponentProps> = ({ comments }) => {

  return (
    <Paper elevation={2} sx={{ padding: 2, paddingTop: 1, marginTop: 2 }}>
      <Box
        className="m-2"
        sx={{
          display: "flex",
          alignItems: "center",
          justifyContent: "space-between",
        }}
      >
        <Typography variant="subtitle1" className="text-sm font-semibold">
          Comments
        </Typography>
      </Box>
      <CommentsListComponent comments={comments} noCommentsToDisplay={2}  />
      <Box
        mt={1}
        sx={{
          display: "flex",
          alignItems: "center",
          gap: 1,
        }}
      >
        <TextField
          fullWidth
          multiline
          rows={1}
          size="small"
          variant="outlined"
          placeholder="Add comments..."
          sx={{
            "& .MuiInputBase-root": {
              fontSize: "0.75rem",
              padding: "4px 8px",
            },
          }}
        />
        <SendIcon className="cursor-pointer"/>
      </Box>
    </Paper>
  );
};

export default CommentComponent;
