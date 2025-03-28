import React from "react";
import { Avatar, Box, Button, List, ListItem, ListItemAvatar, ListItemText, TextField, Typography, Paper, Divider } from "@mui/material";

interface Comment {
  //   id: string;
  //   author: string;
  //   content: string;
  //   avatarUrl: string;
  //   timestamp: string;
  id: string; // Changed from number to string
  content: string;
}

interface CommentComponentProps {
  comments: Comment[];
}

const CommentComponent: React.FC<CommentComponentProps> = ({ comments }) => {
  return (
    <Paper elevation={3} sx={{ padding: 2, marginTop: 2 }}>
      <Typography variant="h6" gutterBottom>
        Comments
      </Typography>
      <List>
        {comments.map((comment) => (
          <React.Fragment key={comment.id}>
            <ListItem alignItems="flex-start">
              <ListItemAvatar>
                <Avatar alt="User" sx={{ width: 28, height: 28 }} />
              </ListItemAvatar>
              <ListItemText primary={<Typography variant="body2">{comment.content}</Typography>} />
            </ListItem>
            <Divider variant="inset" component="li" />
          </React.Fragment>
        ))}
      </List>
      <Box mt={2}>
        <TextField fullWidth multiline rows={1} size="small" variant="outlined" placeholder="Add comments..." sx={{ marginBottom: 1 }} />
        <Button variant="contained" color="primary">
          Add Reply
        </Button>
      </Box>
    </Paper>
  );
};

export default CommentComponent;
