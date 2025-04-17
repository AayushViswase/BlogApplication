import { useEffect, useState } from "react";
import { posts$ } from "../services/api.service";
import { BASE_URL } from "../services/helper";
import CommentComponent from "./Comments";
import OpenInNewIcon from "@mui/icons-material/OpenInNew";

type PostType = {
  postId: string;
  title: string;
  content: string;
  addedDate: string;
  imageName: string;
  category: {
    categoryId: string;
    categoryTitle: string;
  };
  user: {
    userId: string;
    name: string;
    role: string;
    about: string;
  };
  comments: {
    id: string;
    content: string;
  }[];
};

export default function Post() {
  const [posts, setPosts] = useState<PostType[]>([]);
  const [filteredPosts, setFilteredPosts] = useState<PostType[]>([]);
  const [categoryFilter, setCategoryFilter] = useState("all");
  const [userFilter, setUserFilter] = useState("all");
  const [sortOrder, setSortOrder] = useState("newest");

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const response = await posts$();
        const data = response.data.content;
        setPosts(data);
        setFilteredPosts(data);
      } catch (error) {
        console.error("Error fetching posts:", error);
      }
    };
    fetchPosts();
  }, []);

  useEffect(() => {
    let filtered = [...posts];

    if (categoryFilter !== "all") {
      filtered = filtered.filter((post) => post.category.categoryTitle === categoryFilter);
    }

    if (userFilter !== "all") {
      filtered = filtered.filter((post) => post.user.name === userFilter);
    }

    if (sortOrder === "newest") {
      filtered.sort((a, b) => new Date(b.addedDate).getTime() - new Date(a.addedDate).getTime());
    } else {
      filtered.sort((a, b) => new Date(a.addedDate).getTime() - new Date(b.addedDate).getTime());
    }

    setFilteredPosts(filtered);
  }, [categoryFilter, userFilter, sortOrder, posts]);

  const categories = Array.from(new Set(posts.map((post) => post.category.categoryTitle)));
  const users = Array.from(new Set(posts.map((post) => post.user.name)));

  return (
    <div className="bg-white py-4">
      <div className="mx-auto max-w-7xl px-6 lg:px-8">
        <div className="flex flex-col lg:flex-row lg:items-center lg:justify-between mb-6 gap-4">
          <div>
            <h2 className="text-4xl font-semibold tracking-tight text-gray-900 sm:text-5xl">From Our Blog</h2>
            <p className="text-lg text-gray-600">Recent Blog Posts</p>
          </div>
          <div className="mb-8 grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Category</label>
              <select
                value={categoryFilter}
                onChange={(e) => setCategoryFilter(e.target.value)}
                className="block w-full rounded-md border border-gray-300 bg-white px-3 py-2 text-sm shadow-sm focus:border-indigo-500 focus:ring-indigo-500"
              >
                <option value="all">All Categories</option>
                {categories.map((cat, idx) => (
                  <option key={idx} value={cat}>
                    {cat}
                  </option>
                ))}
              </select>
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">User</label>
              <select
                value={userFilter}
                onChange={(e) => setUserFilter(e.target.value)}
                className="block w-full rounded-md border border-gray-300 bg-white px-3 py-2 text-sm shadow-sm focus:border-indigo-500 focus:ring-indigo-500"
              >
                <option value="all">All Users</option>
                {users.map((user, idx) => (
                  <option key={idx} value={user}>
                    {user}
                  </option>
                ))}
              </select>
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Sort By</label>
              <select
                value={sortOrder}
                onChange={(e) => setSortOrder(e.target.value)}
                className="block w-full rounded-md border border-gray-300 bg-white px-3 py-2 text-sm shadow-sm focus:border-indigo-500 focus:ring-indigo-500"
              >
                <option value="newest">Newest First</option>
                <option value="oldest">Oldest First</option>
              </select>
            </div>
            <button
              onClick={() => {
                setCategoryFilter("all");
                setUserFilter("all");
                setSortOrder("newest");
              }}
              className="mt-2 self-end rounded-md border border-gray-300 bg-white px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 shadow-sm"
            >
              Clear Filters
            </button>
          </div>
        </div>

        {/* Posts Grid */}
        <div className="mx-auto mt-6 max-h-full overflow-y-auto grid max-w-2xl grid-cols-1 gap-x-8 gap-y-16 border-t border-gray-200 pt-4 lg:mx-0 lg:max-w-none lg:grid-cols-3">
          {filteredPosts.map((post) => (
            <article key={post.postId} className="flex max-w-xl flex-col items-start justify-between border border-gray-200 p-4 rounded-lg shadow-sm">
              <div className="flex items-center text-xs w-full">
                <div className="flex items-center gap-x-4 flex-grow">
                  <time dateTime={post.addedDate} className="text-gray-500">
                    {new Date(post.addedDate).toDateString()}
                  </time>
                  <a href="#" className="relative z-10 rounded-full bg-gray-50 px-3 py-1.5 font-medium text-gray-600 hover:bg-gray-100">
                    {post.category.categoryTitle}
                  </a>
                </div>
                <OpenInNewIcon className="text-gray-500 hover:text-gray-700 cursor-pointer" />
              </div>

              <div className="group relative">
                <h3 className="mt-3 text-lg font-semibold text-gray-900 group-hover:text-gray-600">
                  <a href="#">
                    <span className="absolute inset-0" />
                    {post.title}
                  </a>
                </h3>
                <p className="mt-5 line-clamp-2 text-sm text-gray-600">{post.content}</p>
              </div>

              <div className="relative mt-8 flex items-center gap-x-4">
                <img alt="Author" src={BASE_URL + "/api/post/image/" + post.imageName} className="size-10 rounded-full bg-gray-50" />
                <div className="text-sm">
                  <p className="font-semibold text-gray-900">
                    <a href="#">
                      <span className="absolute inset-0" />
                      {post.user.name}
                    </a>
                  </p>
                  <p className="text-gray-600">{post.user.role}</p>
                </div>
              </div>

              <div style={{ width: "100%", maxWidth: "100%" }}>
                <CommentComponent comments={post.comments} noCommentsToDisplay={0} />
              </div>
            </article>
          ))}
        </div>
      </div>
    </div>
  );
}
