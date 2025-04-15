import { Button, Dialog, DialogContent, DialogTitle } from "@mui/material";
import * as React from "react";
import CommentsListComponent from "./CommentList";
import { CommentComponentProps } from "./Comments";

interface CommentsDialogComponentProps extends CommentComponentProps {
  open: boolean;
  onClose: () => void;
}

const CommentsDialogComponent: React.FC<CommentsDialogComponentProps> = ({ comments, open, onClose }) => {
  const descriptionElementRef = React.useRef<HTMLElement>(null);

  React.useEffect(() => {
    if (open) {
      const { current: descriptionElement } = descriptionElementRef;
      if (descriptionElement !== null) {
        descriptionElement.focus();
      }
    }
  }, [open]);

  return (
    <Dialog
      open={open}
      onClose={onClose}
      scroll="paper"
      sx={{
        "& .MuiPaper-root": {
          borderRadius: 4,
        },
      }}
    >
      <DialogTitle sx={{ textAlign: "center" }}>Comments</DialogTitle>
      <DialogContent dividers>
        <CommentsListComponent comments={comments} noCommentsToDisplay={comments.length} />
      </DialogContent>
      <Button onClick={onClose}>Close</Button>
    </Dialog>
  );
};

export default CommentsDialogComponent;
