import { Avatar, Divider, List, ListItem, ListItemAvatar, ListItemText, Typography } from "@mui/material";
import React, { useState } from "react";
import { CommentComponentProps } from "./Comments";

const CommentsListComponent: React.FC<CommentComponentProps> = ({ comments, noCommentsToDisplay = 2 }) => {
  const [expandedId, setExpandedId] = useState<string | null>(null);

  const toggleExpand = (id: string) => {
    setExpandedId((prev) => (prev === id ? null : id));
  };
  return (
    <List sx={{ padding: 0, maxWidth: "100%" }}>
      {comments.slice(0, noCommentsToDisplay).map((comment) => {
        const isExpanded = expandedId === comment.id;

        return (
          <React.Fragment key={comment.id}>
            <ListItem alignItems="flex-start" className="cursor-pointer" onClick={() => toggleExpand(comment.id)}>
              <ListItemAvatar>
                <Avatar alt="User" sx={{ width: 28, height: 28 }} />
              </ListItemAvatar>
              <ListItemText
                primary={
                  <Typography
                    variant="body2"
                    className={`text-sm ${isExpanded ? "" : "truncate"}`}
                    sx={{
                      whiteSpace: isExpanded ? "normal" : "nowrap",
                      overflow: "hidden",
                      textOverflow: "ellipsis",
                    }}
                  >
                    {comment.content}
                  </Typography>
                }
              />
            </ListItem>
            <Divider variant="inset" component="li" />
          </React.Fragment>
        );
      })}
    </List>
  );
};
export default CommentsListComponent;
